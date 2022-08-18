package im.flick.camp.fragments

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import im.flick.camp.MainActivity
import im.flick.camp.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import org.matrix.android.sdk.api.Matrix
import org.matrix.android.sdk.api.auth.data.HomeServerConnectionConfig
import im.flick.camp.R
import im.flick.camp.SessionHolder

class SimpleLoginFragment : Fragment() {

    private var _views: FragmentLoginBinding? = null
    private val views get() = _views!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _views = FragmentLoginBinding.inflate(inflater, container, false)
        return views.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity?)?.hideNav("true")
        views.loginButton.setOnClickListener {
            launchAuthProcess()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun launchAuthProcess() {
        val username = views.usernameField.text.toString().trim()
        val password = views.passwordField.text.toString().trim()
        val homeserver = views.homeserverField.text.toString().trim()

        // First, create a homeserver config
        // Be aware than it can throw if you don't give valid info
        val homeServerConnectionConfig = try {
            HomeServerConnectionConfig
                .Builder()
                .withHomeServerUri(Uri.parse(homeserver))
                .build()
        } catch (failure: Throwable) {
            Toast.makeText(requireContext(), "Home server is not valid", Toast.LENGTH_SHORT).show()
            return
        }
        // Then you can retrieve the authentication service.
        // Here we use the direct authentication, but you get LoginWizard and RegistrationWizard for more advanced feature
        //
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                Matrix.getInstance(requireContext()).authenticationService().directAuthentication(
                    homeServerConnectionConfig,
                    username,
                    password,
                    "flickcampdevice"
                )
            } catch (failure: Throwable) {
                Toast.makeText(requireContext(), "Failure: $failure", Toast.LENGTH_SHORT).show()
                null
            }?.let { session ->
                // When you got your session, open and launch sync
                Toast.makeText(
                    requireContext(),
                    "Welcome ${session.myUserId}",
                    Toast.LENGTH_SHORT
                ).show()
                SessionHolder.currentSession = session
                session.open()
                session.startSync(true)
                displayRoomList()
            }
        }
    }

    private fun displayRoomList() {
        val fragment = RoomListFragment()
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment).commit()
    }
}

package im.flick.camp;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import im.flick.camp.fragments.RoomListFragment;
import im.flick.camp.fragments.SimpleLoginFragment;
import me.ibrahimsn.lib.SmoothBottomBar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.matrix.android.sdk.api.Matrix;

public final class MainActivity extends AppCompatActivity {
    private Matrix matrix;
    SmoothBottomBar bottomBarNav;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        this.getWindow().setStatusBarColor(ContextCompat.getColor((Context)this, R.color.colorPrimaryDark));
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);
        this.matrix = Matrix.Companion.getInstance((Context)this);

        if (savedInstanceState == null) {
            if (SessionHolder.INSTANCE.getCurrentSession() != null) {
                this.displayRoomList();
            } else {
                this.displayLogin();
            }
        }
        bottomBarNav = findViewById(R.id.bottomBarNav);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private final void displayLogin() {
        SimpleLoginFragment fragment = new SimpleLoginFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, (Fragment)fragment).commit();
    }

    private final void displayRoomList() {
        RoomListFragment fragment = new RoomListFragment();
        this.getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, (Fragment)fragment).commit();
    }

    public void hideNav(String inpVal){
        if(inpVal=="true"){bottomBarNav.setVisibility(View.GONE);}
        else bottomBarNav.setVisibility(View.VISIBLE);
    }

}

package im.flick.camp.test;
/*
import android.content.Context;
        import android.content.res.Resources;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuInflater;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.Toast;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentActivity;
        import androidx.lifecycle.LifecycleOwnerKt;
        import androidx.lifecycle.Observer;
        import com.stfalcon.chatkit.commons.ImageLoader;
        import com.stfalcon.chatkit.commons.models.IDialog;
        import com.stfalcon.chatkit.dialogs.DialogsListAdapter;
        import com.stfalcon.chatkit.dialogs.DialogsListAdapter.OnDialogClickListener;
        import com.stfalcon.chatkit.utils.DateFormatter.Formatter;
        import im.flick.camp.MainActivity;
        import im.flick.camp.SessionHolder;
        import im.flick.camp.data.RoomSummaryDialogWrapper;
        import im.flick.camp.databinding.FragmentRoomListBinding;
        import im.flick.camp.formatters.RoomListDateFormatter;
import im.flick.camp.fragments.RoomDetailFragment;
import im.flick.camp.helpers.ToolbarConfigurable;
        import im.flick.camp.helpers.ToolbarConfigurable.DefaultImpls;
        import im.flick.camp.utils.AvatarRenderer;
        import im.flick.camp.utils.MatrixItemColorProvider;
        import java.util.ArrayList;
        import java.util.Collection;
        import java.util.Comparator;
        import java.util.Iterator;
        import java.util.List;
        import kotlin.Lazy;
        import kotlin.LazyKt;
        import kotlin.Metadata;
        import kotlin.ResultKt;
        import kotlin.Unit;
        import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
        import kotlin.coroutines.CoroutineContext;
        import kotlin.coroutines.intrinsics.IntrinsicsKt;
        import kotlin.jvm.functions.Function0;
        import kotlin.jvm.functions.Function1;
        import kotlin.jvm.functions.Function2;
        import kotlin.jvm.internal.Intrinsics;
        import kotlinx.coroutines.BuildersKt;
        import kotlinx.coroutines.CoroutineScope;
        import kotlinx.coroutines.CoroutineStart;
        import org.jetbrains.annotations.NotNull;
        import org.jetbrains.annotations.Nullable;
        import org.matrix.android.sdk.api.session.Session;
import org.matrix.android.sdk.api.session.events.model.Event;
import org.matrix.android.sdk.api.session.room.RoomSummaryQueryParams;
        import org.matrix.android.sdk.api.session.room.RoomSummaryQueryParamsKt;
        import org.matrix.android.sdk.api.session.room.model.RoomSummary;
import org.matrix.android.sdk.api.session.room.timeline.TimelineEvent;

@Metadata(
        mv = {1, 4, 2},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001a\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J\u001a\u0010'\u001a\u00020\u00172\u0006\u0010(\u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0010\u0010)\u001a\u00020\u00172\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010,\u001a\u00020\u0017H\u0002J\u0018\u0010-\u001a\u00020\u00172\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020+\u0018\u00010/H\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u00060"},
        d2 = {"Lim/flick/camp/fragments/RoomListFragment;", "Landroidx/fragment/app/Fragment;", "Lim/flick/camp/helpers/ToolbarConfigurable;", "()V", "_views", "Lim/flick/camp/databinding/FragmentRoomListBinding;", "avatarRenderer", "Lim/flick/camp/utils/AvatarRenderer;", "getAvatarRenderer", "()Lim/flick/camp/utils/AvatarRenderer;", "avatarRenderer$delegate", "Lkotlin/Lazy;", "imageLoader", "Lcom/stfalcon/chatkit/commons/ImageLoader;", "roomAdapter", "Lcom/stfalcon/chatkit/dialogs/DialogsListAdapter;", "Lim/flick/camp/data/RoomSummaryDialogWrapper;", "session", "Lorg/matrix/android/sdk/api/session/Session;", "views", "getViews", "()Lim/flick/camp/databinding/FragmentRoomListBinding;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "inflater", "Landroid/view/MenuInflater;", "onCreateView", "Landroid/view/View;", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onViewCreated", "view", "showRoomDetail", "roomSummary", "Lorg/matrix/android/sdk/api/session/room/model/RoomSummary;", "signOut", "updateRoomList", "roomSummaryList", "", "FlickCamp.app"}
)
public final class RoomListFragment extends Fragment implements ToolbarConfigurable {
    private final Session session;
    private FragmentRoomListBinding _views;
    private final Lazy avatarRenderer$delegate;
    private final ImageLoader imageLoader;
    private final DialogsListAdapter roomAdapter;

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MainActivity var10000 = (MainActivity)this.getActivity();
        if (var10000 != null) {
            var10000.hideNav("false");
        }

        this._views = FragmentRoomListBinding.inflate(inflater, container, false);
        return (View)this.getViews().getRoot();
    }

    private final FragmentRoomListBinding getViews() {
        FragmentRoomListBinding var10000 = this._views;
        Intrinsics.checkNotNull(var10000);
        return var10000;
    }

    private final AvatarRenderer getAvatarRenderer() {
        Lazy var1 = this.avatarRenderer$delegate;
        Object var3 = null;
        boolean var4 = false;
        return (AvatarRenderer)var1.getValue();
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Toolbar var10002 = this.getViews().toolbar;
        Intrinsics.checkNotNullExpressionValue(var10002, "views.toolbar");
        this.configureToolbar((Fragment)this, var10002, false);
        this.getViews().roomSummaryList.setAdapter(this.roomAdapter);
        this.roomAdapter.setDatesFormatter((Formatter)(new RoomListDateFormatter()));
        this.roomAdapter.setOnDialogClickListener((OnDialogClickListener)(new OnDialogClickListener() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onDialogClick(IDialog var1) {
                this.onDialogClick((RoomSummaryDialogWrapper)var1);
            }

            public final void onDialogClick(RoomSummaryDialogWrapper it) {
                RoomListFragment.this.showRoomDetail(it.getRoomSummary());
            }
        }));
        RoomSummaryQueryParams roomSummariesQuery = RoomSummaryQueryParamsKt.roomSummaryQueryParams((Function1)null.INSTANCE);
        this.session.getRoomSummariesLive(roomSummariesQuery).observe(this.getViewLifecycleOwner(), (Observer)(new Observer() {
            // $FF: synthetic method
            // $FF: bridge method
            public void onChanged(Object var1) {
                this.onChanged((List)var1);
            }

            public final void onChanged(List it) {
                RoomListFragment.this.updateRoomList(it);
            }
        }));
        this.setHasOptionsMenu(true);
    }

    public void onCreateOptionsMenu(@NotNull Menu menu, @NotNull MenuInflater inflater) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        inflater.inflate(1400001, menu);
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        boolean var10000;
        switch(item.getItemId()) {
            case 1000325:
                this.signOut();
                var10000 = true;
                break;
            default:
                var10000 = super.onOptionsItemSelected(item);
        }

        return var10000;
    }

    private final void signOut() {
        BuildersKt.launch$default((CoroutineScope)LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2((Continuation)null) {
            int label;

            @Nullable
            public final Object invokeSuspend(@NotNull Object $result) {
                FragmentActivity var14;
                Throwable var10000;
                label48: {
                    Object var8 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    boolean var10001;
                    switch(this.label) {
                        case 0:
                            ResultKt.throwOnFailure($result);

                            Object var13;
                            try {
                                Session var12 = RoomListFragment.this.session;
                                this.label = 1;
                                var13 = var12.signOut(true, this);
                            } catch (Throwable var10) {
                                var10000 = var10;
                                var10001 = false;
                                break label48;
                            }

                            if (var13 == var8) {
                                return var8;
                            }
                            break;
                        case 1:
                            try {
                                ResultKt.throwOnFailure($result);
                                break;
                            } catch (Throwable var11) {
                                var10000 = var11;
                                var10001 = false;
                                break label48;
                            }
                        default:
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }

                    try {
                        ;
                    } catch (Throwable var9) {
                        var10000 = var9;
                        var10001 = false;
                        break label48;
                    }

                    SessionHolder.INSTANCE.setCurrentSession((Session)null);
                    var14 = RoomListFragment.this.getActivity();
                    if (var14 != null) {
                        var14.finish();
                    }

                    return Unit.INSTANCE;
                }

                Throwable failure = var10000;
                var14 = RoomListFragment.this.getActivity();
                if (var14 != null) {
                    FragmentActivity var3 = var14;
                    boolean var4 = false;
                    boolean var5 = false;
                    int var7 = false;
                    Toast.makeText((Context)var3, (CharSequence)("Failure: " + failure), 0).show();
                }

                return Unit.INSTANCE;
            }

            @NotNull
            public final Continuation create(@Nullable Object value, @NotNull Continuation completion) {
                Intrinsics.checkNotNullParameter(completion, "completion");
                Function2 var3 = new <anonymous constructor>(completion);
                return var3;
            }

            public final Object invoke(Object var1, Object var2) {
                return ((<undefinedtype>)this.create(var1, (Continuation)var2)).invokeSuspend(Unit.INSTANCE);
            }
        }), 3, (Object)null);
    }

    private final void showRoomDetail(RoomSummary roomSummary) {
        RoomDetailFragment roomDetailFragment = RoomDetailFragment.Companion.newInstance(roomSummary.getRoomId());
        FragmentActivity var10000 = this.getActivity();
        if (var10000 == null) {
            throw new NullPointerException("null cannot be cast to non-null type im.flick.camp.MainActivity");
        } else {
            ((MainActivity)var10000).getSupportFragmentManager().beginTransaction().replace(1000062, (Fragment)roomDetailFragment, "enter_chat").addToBackStack(this.getTag()).commit();
        }
    }

    private final void updateRoomList(List roomSummaryList) {
        if (roomSummaryList != null) {
            Iterable $this$map$iv = (Iterable)roomSummaryList;
            int $i$f$map = false;
            boolean var5 = false;
            $this$map$iv = (Iterable)CollectionsKt.sortedWith($this$map$iv, (Comparator)(new RoomListFragment$updateRoomList$$inlined$sortedByDescending$1()));
            $i$f$map = false;
            Collection destination$iv$iv = (Collection)(new ArrayList(CollectionsKt.collectionSizeOrDefault($this$map$iv, 10)));
            int $i$f$mapTo = false;
            Iterator var8 = $this$map$iv.iterator();

            while(var8.hasNext()) {
                Object item$iv$iv = var8.next();
                RoomSummary it = (RoomSummary)item$iv$iv;
                int var11 = false;
                RoomSummaryDialogWrapper var13 = new RoomSummaryDialogWrapper(it);
                destination$iv$iv.add(var13);
            }

            List sortedRoomSummaryList = (List)destination$iv$iv;
            this.roomAdapter.setItems(sortedRoomSummaryList);
        }
    }

    public RoomListFragment() {
        Session var10001 = SessionHolder.INSTANCE.getCurrentSession();
        Intrinsics.checkNotNull(var10001);
        this.session = var10001;
        this.avatarRenderer$delegate = LazyKt.lazy((Function0)(new Function0() {
            // $FF: synthetic method
            // $FF: bridge method
            public Object invoke() {
                return this.invoke();
            }

            @NotNull
            public final AvatarRenderer invoke() {
                Resources var10004 = RoomListFragment.this.getResources();
                Intrinsics.checkNotNullExpressionValue(var10004, "resources");
                return new AvatarRenderer(new MatrixItemColorProvider(var10004));
            }
        }));
        this.imageLoader = (ImageLoader)(new ImageLoader() {
            public final void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                AvatarRenderer var10000 = RoomListFragment.this.getAvatarRenderer();
                Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
                var10000.render(url, imageView);
            }
        });
        this.roomAdapter = new DialogsListAdapter(this.imageLoader);
    }

    public void configureToolbar(@NotNull AppCompatActivity $this$configureToolbar, @NotNull Toolbar toolbar, boolean displayBack) {
        Intrinsics.checkNotNullParameter($this$configureToolbar, "$this$configureToolbar");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        DefaultImpls.configureToolbar(this, $this$configureToolbar, toolbar, displayBack);
    }

    public void configureToolbar(@NotNull Fragment $this$configureToolbar, @NotNull Toolbar toolbar, boolean displayBack) {
        Intrinsics.checkNotNullParameter($this$configureToolbar, "$this$configureToolbar");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        DefaultImpls.configureToolbar(this, $this$configureToolbar, toolbar, displayBack);
    }
}
final class RoomListFragment$updateRoomList$$inlined$sortedByDescending$1 implements Comparator {
    public final int compare(Object a, Object b) {
        TimelineEvent var10000;
        RoomSummary it;
        boolean var5;
        Event var8;
        Long var9;
        label21: {
            boolean var3 = false;
            it = (RoomSummary)b;
            var5 = false;
            var10000 = it.getLatestPreviewableEvent();
            if (var10000 != null) {
                var8 = var10000.getRoot();
                if (var8 != null) {
                    var9 = var8.getOriginServerTs();
                    break label21;
                }
            }

            var9 = null;
        }

        Comparable var6;
        label16: {
            Comparable var10 = (Comparable)var9;
            it = (RoomSummary)a;
            var6 = var10;
            var5 = false;
            var10000 = it.getLatestPreviewableEvent();
            if (var10000 != null) {
                var8 = var10000.getRoot();
                if (var8 != null) {
                    var9 = var8.getOriginServerTs();
                    break label16;
                }
            }

            var9 = null;
        }

        Long var7 = var9;
        return ComparisonsKt.compareValues(var6, (Comparable)var7);
    }
}
*/
package im.flick.camp.test;
/*
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.os.BundleKt;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView.ItemAnimator;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessageInput.InputListener;
import com.stfalcon.chatkit.messages.MessageInput.TypingListener;
import im.flick.camp.MainActivity;
import im.flick.camp.SessionHolder;
import im.flick.camp.databinding.FragmentRoomDetailBinding;
import im.flick.camp.helpers.ToolbarConfigurable;
import im.flick.camp.utils.AvatarRenderer;
import im.flick.camp.utils.MatrixItemColorProvider;
import im.flick.camp.utils.RecyclerScrollMoreListener;
import im.flick.camp.utils.TimelineEventListProcessor;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.matrix.android.sdk.api.extensions.BooleansKt;
import org.matrix.android.sdk.api.session.Session;
import org.matrix.android.sdk.api.session.room.Room;
import org.matrix.android.sdk.api.session.room.read.ReadService.MarkAsReadParams;
import org.matrix.android.sdk.api.session.room.send.SendService;
import org.matrix.android.sdk.api.session.room.send.SendService.DefaultImpls;
import org.matrix.android.sdk.api.session.room.timeline.Timeline;
import org.matrix.android.sdk.api.session.room.timeline.TimelineSettings;
import org.matrix.android.sdk.api.session.room.timeline.Timeline.Direction;
import org.matrix.android.sdk.api.session.room.timeline.Timeline.Listener;
import org.matrix.android.sdk.api.util.MatrixItem;
import org.matrix.android.sdk.api.util.Optional;
import org.matrix.android.sdk.api.util.MatrixItem.RoomItem;

@Metadata(
        mv = {1, 4, 2},
        bv = {1, 0, 3},
        k = 1,
        d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 92\u00020\u00012\u00020\u00022\u00020\u0003:\u00019B\u0005¢\u0006\u0002\u0010\u0004J&\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020,H\u0016J\u0016\u0010-\u001a\u00020,2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002000/H\u0016J\u0010\u00101\u001a\u00020,2\u0006\u00102\u001a\u000203H\u0016J\u0016\u00104\u001a\u00020,2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060/H\u0016J\u001a\u00107\u001a\u00020,2\u0006\u00108\u001a\u00020$2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010 \u001a\u00020\u00068BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"¨\u0006:"},
        d2 = {"Lim/flick/camp/fragments/RoomDetailFragment;", "Landroidx/fragment/app/Fragment;", "Lorg/matrix/android/sdk/api/session/room/timeline/Timeline$Listener;", "Lim/flick/camp/helpers/ToolbarConfigurable;", "()V", "_views", "Lim/flick/camp/databinding/FragmentRoomDetailBinding;", "adapter", "Lcom/stfalcon/chatkit/messages/MessagesListAdapter;", "Lcom/stfalcon/chatkit/commons/models/IMessage;", "avatarRenderer", "Lim/flick/camp/utils/AvatarRenderer;", "getAvatarRenderer", "()Lim/flick/camp/utils/AvatarRenderer;", "avatarRenderer$delegate", "Lkotlin/Lazy;", "imageLoader", "Lcom/stfalcon/chatkit/commons/ImageLoader;", "mainQuiz", "Lim/flick/camp/MainActivity;", "getMainQuiz", "()Lim/flick/camp/MainActivity;", "setMainQuiz", "(Lim/flick/camp/MainActivity;)V", "room", "Lorg/matrix/android/sdk/api/session/room/Room;", "session", "Lorg/matrix/android/sdk/api/session/Session;", "timeline", "Lorg/matrix/android/sdk/api/session/room/timeline/Timeline;", "timelineEventListProcessor", "Lim/flick/camp/utils/TimelineEventListProcessor;", "views", "getViews", "()Lim/flick/camp/databinding/FragmentRoomDetailBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "", "onNewTimelineEvents", "eventIds", "", "", "onTimelineFailure", "throwable", "", "onTimelineUpdated", "snapshot", "Lorg/matrix/android/sdk/api/session/room/timeline/TimelineEvent;", "onViewCreated", "view", "Companion", "FlickCamp.app"}
)
public final class RoomDetailFragment extends Fragment implements Listener, ToolbarConfigurable {
    @Nullable
    private MainActivity mainQuiz;
    private FragmentRoomDetailBinding _views;
    private final Session session;
    private Timeline timeline;
    private Room room;
    private final Lazy avatarRenderer$delegate;
    private final ImageLoader imageLoader;
    private final MessagesListAdapter adapter;
    private final TimelineEventListProcessor timelineEventListProcessor;
    private static final String ROOM_ID_ARGS = "ROOM_ID_ARGS";
    @NotNull
    public static final RoomDetailFragment.Companion Companion = new RoomDetailFragment.Companion(null);

    @Nullable
    public final MainActivity getMainQuiz() {
        return this.mainQuiz;
    }

    public final void setMainQuiz(@Nullable MainActivity var1) {
        this.mainQuiz = var1;
    }

    private final FragmentRoomDetailBinding getViews() {
        FragmentRoomDetailBinding var10000 = this._views;
        Intrinsics.checkNotNull(var10000);
        return var10000;
    }

    private final AvatarRenderer getAvatarRenderer() {
        Lazy var1 = this.avatarRenderer$delegate;
        Object var3 = null;
        boolean var4 = false;
        return (AvatarRenderer)var1.getValue();
    }

    @Nullable
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        MainActivity var10000 = (MainActivity)this.getActivity();
        if (var10000 != null) {
            var10000.hideNav("true");
        }

        this._views = FragmentRoomDetailBinding.inflate(inflater, container, false);
        return (View)this.getViews().getRoot();
    }

    public void onViewCreated(@NotNull View view, @Nullable Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        Toolbar var10002 = this.getViews().toolbar;
        Intrinsics.checkNotNullExpressionValue(var10002, "views.toolbar");
        this.configureToolbar((Fragment)this, var10002, true);
        this.getViews().textComposer.setInputListener((InputListener)(new InputListener() {
            public final boolean onSubmit(CharSequence it) {
                Room var10000 = RoomDetailFragment.this.room;
                if (var10000 != null) {
                    SendService var2 = (SendService)var10000;
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    DefaultImpls.sendTextMessage$default(var2, it, (String)null, false, 6, (Object)null);
                }

                return true;
            }
        }));
        this.getViews().textComposer.setTypingListener((TypingListener)(new TypingListener() {
            public void onStartTyping() {
                Room var10000 = RoomDetailFragment.this.room;
                if (var10000 != null) {
                    var10000.userIsTyping();
                }

            }

            public void onStopTyping() {
                Room var10000 = RoomDetailFragment.this.room;
                if (var10000 != null) {
                    var10000.userStopsTyping();
                }

            }
        }));
        this.getViews().timelineEventList.setAdapter(this.adapter);
        MessagesList var10000 = this.getViews().timelineEventList;
        Intrinsics.checkNotNullExpressionValue(var10000, "views.timelineEventList");
        var10000.setItemAnimator((ItemAnimator)null);
        var10000 = this.getViews().timelineEventList;
        RecyclerScrollMoreListener var10001 = new RecyclerScrollMoreListener;
        MessagesList var10003 = this.getViews().timelineEventList;
        Intrinsics.checkNotNullExpressionValue(var10003, "views.timelineEventList");
        LayoutManager var19 = var10003.getLayoutManager();
        if (var19 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
        } else {
            RoomDetailFragment var14;
            Timeline var16;
            label27: {
                var10001.<init>((LinearLayoutManager)var19, (Function0)(new Function0() {
                    // $FF: synthetic method
                    // $FF: bridge method
                    public Object invoke() {
                        this.invoke();
                        return Unit.INSTANCE;
                    }

                    public final void invoke() {
                        Timeline var10000 = RoomDetailFragment.this.timeline;
                        if (BooleansKt.orTrue(var10000 != null ? var10000.hasMoreToLoad(Direction.BACKWARDS) : null)) {
                            var10000 = RoomDetailFragment.this.timeline;
                            if (var10000 != null) {
                                var10000.paginate(Direction.BACKWARDS, 50);
                            }
                        }

                    }
                }));
                var10000.addOnScrollListener((OnScrollListener)var10001);
                Bundle var12 = this.getArguments();
                String var13 = var12 != null ? var12.getString("ROOM_ID_ARGS") : null;
                Intrinsics.checkNotNull(var13);
                Intrinsics.checkNotNullExpressionValue(var13, "arguments?.getString(ROOM_ID_ARGS)!!");
                String roomId = var13;
                this.room = this.session.getRoom(roomId);
                BuildersKt.launch$default((CoroutineScope)LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext)null, (CoroutineStart)null, (Function2)(new Function2((Continuation)null) {
                    int label;

                    @Nullable
                    public final Object invokeSuspend(@NotNull Object $result) {
                        Object var3 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        switch(this.label) {
                            case 0:
                                ResultKt.throwOnFailure($result);
                                Room var10000 = RoomDetailFragment.this.room;
                                if (var10000 != null) {
                                    Room var2 = var10000;
                                    MarkAsReadParams var10001 = MarkAsReadParams.READ_RECEIPT;
                                    this.label = 1;
                                    if (var2.markAsRead(var10001, this) == var3) {
                                        return var3;
                                    }
                                }
                                break;
                            case 1:
                                ResultKt.throwOnFailure($result);
                                break;
                            default:
                                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
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
                        return this.create(var1, (Continuation)var2);
                    }
                }), 3, (Object)null);
                TimelineSettings timelineSettings = new TimelineSettings(30, false, 2,);
                var14 = this;
                Room var15 = this.room;
                if (var15 != null) {
                    var16 = var15.createTimeline((String)null, timelineSettings);
                    if (var16 != null) {
                        Timeline var5 = var16;
                        boolean var6 = false;
                        boolean var7 = false;
                        int var9 = false;
                        var5.addListener((Listener)this);
                        var5.start();
                        Unit var11 = Unit.INSTANCE;
                        var14 = this;
                        var16 = var5;
                        break label27;
                    }
                }

                var16 = null;
            }

            var14.timeline = var16;
            Room var17 = this.room;
            if (var17 != null) {
                LiveData var18 = var17.getRoomSummaryLive();
                if (var18 != null) {
                    var18.observe(this.getViewLifecycleOwner(), (Observer)(new Observer() {
                        // $FF: synthetic method
                        // $FF: bridge method
                        public void onChanged(Object var1) {
                            this.onChanged((Optional)var1);
                        }

                        public final void onChanged(Optional roomSummary) {
                            RoomItem var10000 = (RoomItem)roomSummary.map((Function1)null).getOrNull();
                            if (var10000 != null) {
                                RoomItem roomSummaryAsMatrixItem = var10000;
                                TextView var3 = RoomDetailFragment.this.getViews().toolbarTitleView;
                                Intrinsics.checkNotNullExpressionValue(var3, "views.toolbarTitleView");
                                var3.setText((CharSequence)("@" + roomSummaryAsMatrixItem.getBestName()));
                                AvatarRenderer var4 = RoomDetailFragment.this.getAvatarRenderer();
                                MatrixItem var10001 = (MatrixItem)roomSummaryAsMatrixItem;
                                ImageView var10002 = RoomDetailFragment.this.getViews().toolbarAvatarImageView;
                                Intrinsics.checkNotNullExpressionValue(var10002, "views.toolbarAvatarImageView");
                                var4.render(var10001, var10002);
                            }
                        }
                    }));
                }
            }

        }
    }

    public void onDestroyView() {
        Timeline var10000 = this.timeline;
        if (var10000 != null) {
            Timeline var1 = var10000;
            boolean var2 = false;
            boolean var3 = false;
            //int var5 = false;
            var1.removeAllListeners();
            var1.dispose();
        }

        this.timeline = (Timeline)null;
        this.room = (Room)null;
        super.onDestroyView();
        //this._$_clearFindViewByIdCache();
    }

    public void onNewTimelineEvents(@NotNull List eventIds) {
        Intrinsics.checkNotNullParameter(eventIds, "eventIds");
    }

    public void onTimelineFailure(@NotNull Throwable throwable) {
        Intrinsics.checkNotNullParameter(throwable, "throwable");
    }

    public void onTimelineUpdated(@NotNull List snapshot) {
        Intrinsics.checkNotNullParameter(snapshot, "snapshot");
        this.timelineEventListProcessor.onNewSnapshot(snapshot);
    }

    public RoomDetailFragment() {
        Session var10001 = SessionHolder.INSTANCE.getCurrentSession();
        Intrinsics.checkNotNull(var10001);
        this.session = var10001;
        this.avatarRenderer$delegate = LazyKt.lazy((Function0)(new Function0() {
            // $FF: synthetic method
            // $FF: bridge method
            //public Object invoke() {
            //    return this.invoke();
           // }

            @NotNull
            public final AvatarRenderer invoke() {
                Resources var10004 = RoomDetailFragment.this.getResources();
                Intrinsics.checkNotNullExpressionValue(var10004, "resources");
                return new AvatarRenderer(new MatrixItemColorProvider(var10004));
            }
        }));
        this.imageLoader = (ImageLoader)(new ImageLoader() {
            public final void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                AvatarRenderer var10000 = RoomDetailFragment.this.getAvatarRenderer();
                Intrinsics.checkNotNullExpressionValue(imageView, "imageView");
                var10000.render(url, imageView);
            }
        });
        this.adapter = new MessagesListAdapter(this.session.getMyUserId(), this.imageLoader);
        this.timelineEventListProcessor = new TimelineEventListProcessor(this.adapter);
    }

    public void configureToolbar(@NotNull AppCompatActivity $this$configureToolbar, @NotNull Toolbar toolbar, boolean displayBack) {
        Intrinsics.checkNotNullParameter($this$configureToolbar, "$this$configureToolbar");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        im.flick.camp.helpers.ToolbarConfigurable.DefaultImpls.configureToolbar(this, $this$configureToolbar, toolbar, displayBack);
    }

    public void configureToolbar(@NotNull Fragment $this$configureToolbar, @NotNull Toolbar toolbar, boolean displayBack) {
        Intrinsics.checkNotNullParameter($this$configureToolbar, "$this$configureToolbar");
        Intrinsics.checkNotNullParameter(toolbar, "toolbar");
        im.flick.camp.helpers.ToolbarConfigurable.DefaultImpls.configureToolbar(this, $this$configureToolbar, toolbar, displayBack);
    }

    // $FF: synthetic method
    public static final void access$setRoom$p(RoomDetailFragment $this, Room var1) {
        $this.room = var1;
    }

    // $FF: synthetic method
    public static final void access$setTimeline$p(RoomDetailFragment $this, Timeline var1) {
        $this.timeline = var1;
    }

    @Metadata(
            mv = {1, 4, 2},
            bv = {1, 0, 3},
            k = 1,
            d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"},
            d2 = {"Lim/flick/camp/fragments/RoomDetailFragment$Companion;", "", "()V", "ROOM_ID_ARGS", "", "newInstance", "Lim/flick/camp/fragments/RoomDetailFragment;", "roomId", "FlickCamp.app"}
    )
    public static final class Companion {
        @NotNull
        public final RoomDetailFragment newInstance(@NotNull String roomId) {
            Intrinsics.checkNotNullParameter(roomId, "roomId");
            Bundle args = BundleKt.bundleOf(new Pair[]{new Pair("ROOM_ID_ARGS", roomId)});
            RoomDetailFragment var3 = new RoomDetailFragment();
            boolean var4 = false;
            boolean var5 = false;
            //int var7 = false;
            var3.setArguments(args);
            return var3;
        }

        private Companion() {
        }

        // $FF: synthetic method
        //public Companion(DefaultConstructorMarker $constructor_marker) {
        //    this();
       // }
    }
}
*/
package com.jesatadila.expandableview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


/**
 * Created by Nuhel on 11/14/2017.
 */



public class MyExpandable extends FrameLayout {

    private boolean animateExpand;
    private int backgroundColor;
    private int headerBackgroundColor;
    private Drawable expandIndicatorimage;

    private FrameLayout bgCard;
    private ImageView expandIcon;

    private boolean isExpanded;
    private int expandableViewHeight;
    private int collapsedViewHeight;

    private View headerView;
    private View expandableView;
    private LayoutParams expandableViewParams;

    private MyExpandingListener expandingListener;

    private int headerheight;
    private int bodyheight;

    public MyExpandable(Context context) {
        this(context, null, 0);
    }

    public MyExpandable(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyExpandable(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(attrs);
        initView();
        initExpandableClick();
    }

    private void initAttrs(AttributeSet attrs) {
        if (attrs == null) return;
        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.MyExpandable,
                0, 0);
        collapsedViewHeight = a.getDimensionPixelSize(R.styleable.MyExpandable_headerhight,0);

        animateExpand = true;
        backgroundColor =  a.getColor(R.styleable.MyExpandable_body_background_color,Color.BLUE);
        headerBackgroundColor = a.getColor(R.styleable.MyExpandable_header_background_color,Color.GREEN);

        expandIndicatorimage= a.getDrawable(R.styleable.MyExpandable_indicatorimage);
        if(expandIndicatorimage==null){
            expandIndicatorimage =   getContext().getResources().getDrawable(R.drawable.downarrow);
        }

        a.recycle();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        buildView();
    }

    private void buildView() {
        headerView = getChildAt(1);
        expandableView = getChildAt(2);

        if (headerView == null) {
            return;
        }

        if (expandableView == null) {
            return;
        }

        if (getChildCount() > 3) {
            throw new IllegalStateException("ExpandableView can have only two child views" + getChildCount());
        }

        removeView(headerView);
        removeView(expandableView);

        expandableViewParams = (FrameLayout.LayoutParams) expandableView.getLayoutParams();
        expandableView.measure(0, 0);
        expandableViewHeight = expandableView.getMeasuredHeight();
        expandableViewParams.height = 0;
        expandableViewParams.setMargins(0, collapsedViewHeight, 0, 0);
        expandableView.setLayoutParams(expandableViewParams);

        bgCard.addView(expandableView);
        buildHeader();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams thisParams = getLayoutParams();

        thisParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        thisParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        this.setLayoutParams(thisParams);
    }

    private void initView() {
        initBackground();
    }

    private void initBackground() {
        if (bgCard == null) {
            bgCard = new FrameLayout(getContext());
        }

        LinearLayout.LayoutParams bgParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        bgCard.setLayoutParams(bgParams);
        bgCard.setBackgroundColor(backgroundColor);

        for (int i = 0; i < getChildCount(); i++) {
            if (getChildAt(i).equals(bgCard)) return;
        }
        addView(bgCard);
        setBackgroundColor(headerBackgroundColor);
    }

    private void buildHeader() {
        RelativeLayout headerLayout = new RelativeLayout(getContext());
        FrameLayout.LayoutParams headerViewParams = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, collapsedViewHeight);
        headerLayout.setLayoutParams(headerViewParams);
        bgCard.addView(headerLayout);
        initExpandIcon(headerLayout);
        headerLayout.setBackgroundColor(headerBackgroundColor);
        initHeaderContent(headerLayout);
    }


    private void initExpandIcon(RelativeLayout headerLayout) {
        expandIcon = new ImageView(getContext());

        int margin = dpToPx(8);;

        RelativeLayout.LayoutParams expandIconParams = new RelativeLayout.LayoutParams(dpToPx(24), dpToPx(24));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            expandIconParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        } else {
            expandIconParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        }
        expandIconParams.addRule(RelativeLayout.CENTER_VERTICAL);
        expandIconParams.setMargins(margin, margin, margin, margin);

        expandIcon.setId(MyExpandableUtils.ID_EXPAND_ICON);
        expandIcon.setLayoutParams(expandIconParams);
        expandIcon.setImageDrawable(expandIndicatorimage);
        headerLayout.addView(expandIcon);
    }

    private void initHeaderContent(RelativeLayout headerLayout) {
        FrameLayout headerContent = new FrameLayout(getContext());

        int margin = dpToPx(8);
        RelativeLayout.LayoutParams headerContentParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            headerContentParams.addRule(RelativeLayout.END_OF, MyExpandableUtils.ID_HEADER_ICON);
            headerContentParams.addRule(RelativeLayout.START_OF, MyExpandableUtils.ID_EXPAND_ICON);
        } else {
            headerContentParams.addRule(RelativeLayout.RIGHT_OF, MyExpandableUtils.ID_HEADER_ICON);
            headerContentParams.addRule(RelativeLayout.LEFT_OF, MyExpandableUtils.ID_EXPAND_ICON);
        }
        headerContentParams.addRule(RelativeLayout.CENTER_VERTICAL);

        headerContentParams.setMargins(margin, margin / 2, margin, margin / 2);

        headerContent.setLayoutParams(headerContentParams);
        headerContent.addView(headerView);
        headerLayout.addView(headerContent);
    }

    private void initExpandableClick() {
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (animateExpand) {
                    if (!isExpanded) {
                        expandWithAnimation();
                    } else {
                        collapseWithAnimation();
                    }
                } else {
                    if (!isExpanded) {
                        expand();
                    } else {
                        collapse();
                    }
                }
            }
        });
    }

    private void expand() {
        expandableViewParams.height = expandableViewHeight;
        expandableView.setLayoutParams(expandableViewParams);
        expandIcon.setRotation(180f);
        isExpanded = true;
        if (expandingListener != null) {
            expandingListener.onExpanded();
        }
    }

    private void expandWithAnimation() {


        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(expandIcon, "rotation", 0f, 180f);

        ValueAnimator expandAnimator = ValueAnimator.ofInt(0, expandableViewHeight);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                expandableViewParams.height = (int) valueAnimator.getAnimatedValue();
                expandableView.setLayoutParams(expandableViewParams);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether(  rotationAnimator, expandAnimator);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(200);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (expandingListener != null) {
                    expandingListener.onExpanded();
                }
            }
        });

        set.start();
        isExpanded = true;
    }

    private void collapse() {
        expandableViewParams.height = 0;
        expandableView.setLayoutParams(expandableViewParams);
        expandIcon.setRotation(0);
        isExpanded = false;
        if (expandingListener != null) {
            expandingListener.onCollapsed();
        }
    }

    private void collapseWithAnimation() {
        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(expandIcon, "rotation", 180f, 0f);
        ValueAnimator expandAnimator = ValueAnimator.ofInt(expandableViewHeight, 0);
        expandAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                expandableViewParams.height = (int) valueAnimator.getAnimatedValue();
                expandableView.setLayoutParams(expandableViewParams);
            }
        });

        AnimatorSet set = new AnimatorSet();
        set.playTogether( rotationAnimator, expandAnimator);
        set.setInterpolator(new DecelerateInterpolator());
        set.setDuration(200);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (expandingListener != null) {
                    expandingListener.onCollapsed();
                }
            }
        });

        set.start();
        isExpanded = false;
    }

    public Drawable getIcon() {
        return null;
    }

    public boolean isAnimateExpand() {
        return animateExpand;
    }

    public void setAnimateExpand(boolean animateExpand) {
        this.animateExpand = animateExpand;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void changeBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getHeaderBackgroundColor() {
        return headerBackgroundColor;
    }

    public void setHeaderBackgroundColor(int headerBackgroundColor) {
        this.headerBackgroundColor = headerBackgroundColor;
    }

    public void setExpandIndicatorDrawable(Drawable expandIndicator) {
        this.expandIndicatorimage = expandIndicator;
    }

    public void setExpandIndicator(@DrawableRes int expandIndicatorRes) {
        expandIndicatorimage = ContextCompat.getDrawable(getContext(), expandIndicatorRes);
    }

    public void setExpandingListener(MyExpandingListener expandingListener) {
        this.expandingListener = expandingListener;
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = getContext().getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}

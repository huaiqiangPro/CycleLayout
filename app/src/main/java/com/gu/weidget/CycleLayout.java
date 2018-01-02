package com.gu.weidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

import com.gu.cyclelayout.R;


/**
 * 作用：圆角相对布局
 * 作者：mr.gu
 */
public class CycleLayout extends RelativeLayout {
    private float[] radii = new float[8];   // top-left, top-right, bottom-right, bottom-left
    private Path mClipPath;                 // 剪裁区域路径
    private Paint mPaint;                   // 画笔
    private boolean mRoundAsCircle = false; // 圆形
    private int mStrokeColor;               // 描边颜色
    private int mStrokeWidth;               // 描边半径
    private Region mAreaRegion;             // 内容区域
    private int mEdgeFix = 10;              // 边缘修复
    private RectF mLayer;                   // 画布图层大小

    public CycleLayout(Context context) {
        this(context, null);
    }

    public CycleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CycleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CycleLayout);
        mRoundAsCircle = ta.getBoolean(R.styleable.CycleLayout_round_as_circle, false);
        mStrokeColor = ta.getColor(R.styleable.CycleLayout_stroke_color, Color.WHITE);
        mStrokeWidth = ta.getDimensionPixelSize(R.styleable.CycleLayout_stroke_width, 0);
        int roundCorner = ta.getDimensionPixelSize(R.styleable.CycleLayout_round_corner, 0);
        int roundCornerTopLeft = ta.getDimensionPixelSize(
                R.styleable.CycleLayout_round_corner_top_left, roundCorner);
        int roundCornerTopRight = ta.getDimensionPixelSize(
                R.styleable.CycleLayout_round_corner_top_right, roundCorner);
        int roundCornerBottomLeft = ta.getDimensionPixelSize(
                R.styleable.CycleLayout_round_corner_bottom_left, roundCorner);
        int roundCornerBottomRight = ta.getDimensionPixelSize(
                R.styleable.CycleLayout_round_corner_bottom_right, roundCorner);
        ta.recycle();

        radii[0] = roundCornerTopLeft;
        radii[1] = roundCornerTopLeft;

        radii[2] = roundCornerTopRight;
        radii[3] = roundCornerTopRight;

        radii[4] = roundCornerBottomRight;
        radii[5] = roundCornerBottomRight;

        radii[6] = roundCornerBottomLeft;
        radii[7] = roundCornerBottomLeft;

        mClipPath = new Path();
        mAreaRegion = new Region();
        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setAntiAlias(true);
    }

    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mLayer = new RectF(0, 0, w, h);
        RectF areas = new RectF();
        areas.left = getPaddingLeft();
        areas.top = getPaddingTop();
        areas.right = w - getPaddingRight();
        areas.bottom = h - getPaddingBottom();
        mClipPath.reset();
        if (mRoundAsCircle) {
            float d = areas.width() >= areas.height() ? areas.height() : areas.width();
            float r = d / 2;
            PointF center = new PointF(w / 2, h / 2);
            mClipPath.addCircle(center.x, center.y, r, Path.Direction.CW);
            mClipPath.moveTo(-mEdgeFix, -mEdgeFix);  // 通过空操作让Path区域占满画布
            mClipPath.moveTo(w + mEdgeFix, h + mEdgeFix);
        } else {
            mClipPath.addRoundRect(areas, radii, Path.Direction.CW);
        }
        Region clip = new Region((int) areas.left, (int) areas.top,
                                 (int) areas.right, (int) areas.bottom);
        mAreaRegion.setPath(mClipPath, clip);
    }

    @Override protected void dispatchDraw(Canvas canvas) {
        canvas.saveLayer(mLayer, null, Canvas.ALL_SAVE_FLAG);
        super.dispatchDraw(canvas);
        if (mStrokeWidth > 0) {
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER));
            mPaint.setStrokeWidth(mStrokeWidth * 2);
            mPaint.setColor(mStrokeColor);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawPath(mClipPath, mPaint);
        }
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(mClipPath, mPaint);
        canvas.restore();
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!mAreaRegion.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }
}

package com.barnettwong.autofitcolortextview_library;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * A {@link AppCompatTextView} that re-sizes its text to be no larger than the width of the view.
 *
 * @attr ref R.styleable.AutofitTextView_sizeToFit
 * @attr ref R.styleable.AutofitTextView_minTextSize
 * @attr ref R.styleable.AutofitTextView_precision
 */
public class AutoFitColorTextView extends AppCompatTextView implements AutofitHelper.OnTextSizeChangeListener {

    private AutofitHelper mHelper;
    
    public boolean isClickSpan = false;
    private String[] colorArr,sizeArr,styleArr;

    public AutoFitColorTextView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public AutoFitColorTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public AutoFitColorTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mHelper = AutofitHelper.create(this, attrs, defStyle)
                .addOnTextSizeChangeListener(this);
    }

    // Getters and Setters

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTextSize(int unit, float size) {
        super.setTextSize(unit, size);
        if (mHelper != null) {
            mHelper.setTextSize(unit, size);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLines(int lines) {
        super.setLines(lines);
        if (mHelper != null) {
            mHelper.setMaxLines(lines);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMaxLines(int maxLines) {
        super.setMaxLines(maxLines);
        if (mHelper != null) {
            mHelper.setMaxLines(maxLines);
        }
    }

    /**
     * Returns the {@link AutofitHelper} for this View.
     */
    public AutofitHelper getAutofitHelper() {
        return mHelper;
    }

    /**
     * Returns whether or not the text will be automatically re-sized to fit its constraints.
     */
    public boolean isSizeToFit() {
        return mHelper.isEnabled();
    }

    /**
     * Sets the property of this field (sizeToFit), to automatically resize the text to fit its
     * constraints.
     */
    public void setSizeToFit() {
        setSizeToFit(true);
    }

    /**
     * If true, the text will automatically be re-sized to fit its constraints; if false, it will
     * act like a normal TextView.
     *
     * @param sizeToFit
     */
    public void setSizeToFit(boolean sizeToFit) {
        mHelper.setEnabled(sizeToFit);
    }

    /**
     * Returns the maximum size (in pixels) of the text in this View.
     */
    public float getMaxTextSize() {
        return mHelper.getMaxTextSize();
    }

    /**
     * Set the maximum text size to the given value, interpreted as "scaled pixel" units. This size
     * is adjusted based on the current density and user font size preference.
     *
     * @param size The scaled pixel size.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    public void setMaxTextSize(float size) {
        mHelper.setMaxTextSize(size);
    }

    /**
     * Set the maximum text size to a given unit and value. See TypedValue for the possible
     * dimension units.
     *
     * @param unit The desired dimension unit.
     * @param size The desired size in the given units.
     *
     * @attr ref android.R.styleable#TextView_textSize
     */
    public void setMaxTextSize(int unit, float size) {
        mHelper.setMaxTextSize(unit, size);
    }

    /**
     * Returns the minimum size (in pixels) of the text in this View.
     */
    public float getMinTextSize() {
        return mHelper.getMinTextSize();
    }

    /**
     * Set the minimum text size to the given value, interpreted as "scaled pixel" units. This size
     * is adjusted based on the current density and user font size preference.
     *
     * @param minSize The scaled pixel size.
     *
     * @attr ref me.grantland.R.styleable#AutofitTextView_minTextSize
     */
    public void setMinTextSize(int minSize) {
        mHelper.setMinTextSize(TypedValue.COMPLEX_UNIT_SP, minSize);
    }

    /**
     * Set the minimum text size to a given unit and value. See TypedValue for the possible
     * dimension units.
     *
     * @param unit The desired dimension unit.
     * @param minSize The desired size in the given units.
     *
     * @attr ref me.grantland.R.styleable#AutofitTextView_minTextSize
     */
    public void setMinTextSize(int unit, float minSize) {
        mHelper.setMinTextSize(unit, minSize);
    }

    /**
     * Returns the amount of precision used to calculate the correct text size to fit within its
     * bounds.
     */
    public float getPrecision() {
        return mHelper.getPrecision();
    }

    /**
     * Set the amount of precision used to calculate the correct text size to fit within its
     * bounds. Lower precision is more precise and takes more time.
     *
     * @param precision The amount of precision.
     */
    public void setPrecision(float precision) {
        mHelper.setPrecision(precision);
    }

    @Override
    public void onTextSizeChange(float textSize, float oldTextSize) {
        // do nothing
    }

    @Override
    public boolean performClick() {
        if (isClickSpan){
            return true;
        }
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        isClickSpan = false;
        return super.onTouchEvent(event);
    }

    /**
     * 设置文本内容其中文字的颜色
     * @param text   内容
     * @param color  颜色
     */
    public AutoFitColorTextView setTextArrColor(String text, int color){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的颜色
     * @param text   内容
     * @param color  颜色
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColor(final String text, final int color, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(color);
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的颜色
     * @param text   内容
     * @param color  颜色
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrColor(String text, int color, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的颜色
     * @param text   内容
     * @param color  颜色
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColor(final String text, final int color, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(color);
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());

        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小
     * @param text  内容
     * @param size  字体大小
     */
    public AutoFitColorTextView setTextArrSize(String text, int size){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小
     * @param text  内容
     * @param size  字体大小
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrSize(final String text, int size, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(getTextColors().getDefaultColor());
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小
     * @param text  内容
     * @param size  字体大小
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrSize(String text, int size, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小
     * @param text  内容
     * @param size  字体大小
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrSize(final String text, int size, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(getTextColors().getDefaultColor());
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的字体样式
     * @param text   内容
     * @param style  字体样式
     */
    public AutoFitColorTextView setTextArrStyle(String text, int style){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体样式
     * @param text   内容
     * @param style  字体样式
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrStyle(final String text, int style, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(getTextColors().getDefaultColor());
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体样式
     * @param text   内容
     * @param style  字体样式
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrStyle(String text, int style, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体样式
     * @param text   内容
     * @param style  字体样式
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrStyle(final String text, int style, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(getTextColors().getDefaultColor());
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     */
    public AutoFitColorTextView setTextArrColorSize(String text, int color, int size){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorSize(final String text, final int color, int size, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(color);
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrColorSize(String text, int color, int size, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorSize(final String text, final int color, int size, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(color);
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param style  字体样式
     */
    public AutoFitColorTextView setTextArrColorStyle(String text, int color, int style){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param style  字体样式
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorStyle(final String text, final int color, int style, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(color);
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param style  字体样式
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrColorStyle(String text, int color, int style, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param style  字体样式
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorStyle(final String text, final int color, int style, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(color);
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小，字体样式
     * @param text   内容
     * @param size   字体大小
     * @param style  字体样式
     */
    public AutoFitColorTextView setTextArrSizeStyle(String text, int size, int style){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小，字体样式
     * @param text   内容
     * @param size   字体大小
     * @param style  字体样式
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrSizeStyle(final String text, int size, int style, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(getTextColors().getDefaultColor());
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小，字体样式
     * @param text   内容
     * @param size   字体大小
     * @param style  字体样式
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrSizeStyle(String text, int size, int style, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体大小，字体样式
     * @param text   内容
     * @param size   字体大小
     * @param style  字体样式
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrSizeStyle(final String text, int size, int style, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(getTextColors().getDefaultColor());
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param style  字体样式
     */
    public AutoFitColorTextView setTextArrColorSizeStyle(String text, int color, int size, int style){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param style  字体样式
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorSizeStyle(final String text, final int color, int size, int style, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(color);
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param style  字体样式
     * @param startIndex  开始位置
     */
    public AutoFitColorTextView setTextArrColorSizeStyle(String text, int color, int size, int style, int startIndex){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ForegroundColorSpan(color),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        return this;
    }

    /**
     * 设置文本内容其中文字的字体颜色，字体大小，字体样式
     * @param text   内容
     * @param color  字体颜色
     * @param size   字体大小
     * @param style  字体样式
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextArrColorSizeStyle(final String text, final int color, int size, int style, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new AbsoluteSizeSpan(DensityUtil.dp2px(getContext(),size)),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new StyleSpan(style),startIndex,startIndex+text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(color);
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());
        return this;
    }

    /**
     * 设置文本内容其中文字的点击事件
     * @param text   内容
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextClick(final String text, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        int startIndex = (getText()+"").indexOf(text);
        if (startIndex>-1){
            styledText.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View widget) {
                    onClickSpan.onClick(text);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    super.updateDrawState(ds);
                    //设置文本的颜色
                    ds.setColor(getTextColors().getDefaultColor());
                    //超链接形式的下划线，false 表示不
                    ds.setUnderlineText(false);
                }
            }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(styledText, BufferType.SPANNABLE);
            setMovementMethod(CustomLinkMovementMethod.getInstance());
        }

        return this;
    }

    /**
     * 设置文本内容其中文字的点击事件
     * @param text   内容
     * @param startIndex  开始位置
     * @param onClickSpan  点击事件
     */
    public AutoFitColorTextView setTextClick(final String text, int startIndex, final AutoFitColorTextView.OnClickSpan onClickSpan){
        if (getText().length()==0){
            throw new NullPointerException("Please Set The textView Content!");
        }
        SpannableString styledText = new SpannableString(getText());
        styledText.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                onClickSpan.onClick(text);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                //设置文本的颜色
                ds.setColor(getTextColors().getDefaultColor());
                //超链接形式的下划线，false 表示不
                ds.setUnderlineText(false);
            }
        }, startIndex, startIndex + text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        setText(styledText, BufferType.SPANNABLE);
        setMovementMethod(CustomLinkMovementMethod.getInstance());

        return this;
    }

    public interface OnClickSpan{
        void onClick(String text);
    }
}

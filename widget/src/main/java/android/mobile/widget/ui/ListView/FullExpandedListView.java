
package android.mobile.widget.ui.ListView;

import android.widget.ListView;

// 完全展开ListView中的各项,一般与ScrollView一块使用
public class FullExpandedListView extends ListView {

    public FullExpandedListView(android.content.Context context,
            android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}

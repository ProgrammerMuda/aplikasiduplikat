package maj.raga.aplikasiduplikat;

import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import maj.raga.aplikasiduplikat.Function.functions;

public class colorActivity extends functions {

    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        myIntent(HomeActivity.class);
        finish();
    }
}

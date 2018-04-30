package maj.raga.aplikasiduplikat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import maj.raga.aplikasiduplikat.Function.functions;


public class ShapeActivity extends functions {
    ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
        ButterKnife.bind(this);
        img = (ImageView) findViewById(R.id.back);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ShapeActivity.this, HomeActivity.class));
                finish();
            }
        });
    }

}

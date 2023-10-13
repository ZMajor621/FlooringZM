package css.flooring;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView textViewResult;
    Button buttonReturn;
    Double width;
    Double length;
    Double flooring;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        textViewResult = findViewById(R.id.textViewResult);

        Bundle extras = getIntent().getExtras();
        width = extras.getDouble("MainWidth");
        length = extras.getDouble("MainLength");
        flooring = width*length;
        textViewResult.setText("Width is " + width + " Length is " + length + " and flooring needed is " + flooring + ".");
        setupReturnButton();
    }

    private void setupReturnButton() {
        buttonReturn = findViewById(R.id.buttonReturn);
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CIS 3334", "Return button clicked");   // log button click for debugging using "CIS 3334" tag
                Intent intent = new Intent();
                intent.putExtra("NewFlooring", flooring);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
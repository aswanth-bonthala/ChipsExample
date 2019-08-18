package com.truiton.chipsexample;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipDrawable;
import com.google.android.material.chip.ChipGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "Chips Example";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Action chip section*/
        Chip actionChip = findViewById(R.id.action_chip);
        actionChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Clicked action chip");
            }
        });

        /*Entry chip section*/
        final ChipGroup entryChipGroup = findViewById(R.id.entry_chip_group);
        final Chip entryChip = getChip(entryChipGroup, "Hello World");
        final Chip entryChip2 = getChip(entryChipGroup, "Test");
        entryChipGroup.addView(entryChip);
        entryChipGroup.addView(entryChip2);

        /*Filter Chip section*/
        ChipGroup filterChipGroup = findViewById(R.id.filter_chip_group);
        Chip filterChip = findViewById(R.id.filter_chip);
        Chip filterChip2 = findViewById(R.id.filter_chip2);
        CompoundButton.OnCheckedChangeListener filterChipListener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.i(TAG, buttonView.getId() + "");
            }
        };
        filterChip.setOnCheckedChangeListener(filterChipListener);
        filterChip2.setOnCheckedChangeListener(filterChipListener);

        /*Filter Chip section - Single selection mode*/
        ChipGroup filterChipGroupSingleSelection = findViewById(R.id.filter_chip_SS_group);
        filterChipGroupSingleSelection.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup group, @IdRes int checkedId) {
                // Handle the checked chip change.
                Log.i(TAG, "ID: " + group.getCheckedChipId());
            }
        });

        /*Choice Chip Section*/
        ChipGroup choiceChipGroup = findViewById(R.id.choice_chip_group);
        choiceChipGroup.setOnCheckedChangeListener(new ChipGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ChipGroup chipGroup, @IdRes int i) {
                Log.i(TAG, chipGroup.getCheckedChipId() + "");

                switch (chipGroup.getCheckedChipId())
                {
                    case R.id.choice_chip:
                        Toast.makeText(MainActivity.this, "Cars selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.choice_chip2:
                        Toast.makeText(MainActivity.this, "Trucks Selected", Toast.LENGTH_SHORT).show();
                        break;

                }

            }
        });

    }

    private Chip getChip(final ChipGroup entryChipGroup, String text) {
        final Chip chip = new Chip(this);
        chip.setChipDrawable(ChipDrawable.createFromResource(this, R.xml.my_chip));
        int paddingDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10,
                getResources().getDisplayMetrics()
        );
        chip.setPadding(paddingDp, paddingDp, paddingDp, paddingDp);
        chip.setText(text);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entryChipGroup.removeView(chip);
            }
        });
        return chip;
    }
}

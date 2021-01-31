package ru.mperika.smartshoppinglist.ui.edit

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.ProductCategory
import java.text.SimpleDateFormat
import java.util.*

class ProductEditActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView
    private lateinit var nameText: TextView
    private lateinit var brandText: TextView
    private lateinit var quantityFld: TextView
    private lateinit var typeSpnr: Spinner
    private lateinit var fridgeSwitch: Switch
    private lateinit var calendarView: TextView
    private lateinit var saveBtn: Button
    private lateinit var cancelBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_edit)

        imageView = findViewById(R.id.imageView)
        nameText = findViewById(R.id.name_text)
        brandText = findViewById(R.id.brand_text)
        quantityFld = findViewById(R.id.quantity_fld)
        createTypeSpinner()

        fridgeSectionCreate()
        typeSpnr.onItemSelectedListener = object: OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,
                position: Int, id: Long) {

                fridgeSwitch.isChecked = typeSpnr.selectedItem == ProductCategory.MEAL_WITH_EXP_DATE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        saveBtn = findViewById(R.id.save_button)
        cancelBtn = findViewById(R.id.cancel_button1)
    }

    private fun createTypeSpinner() {
        typeSpnr = findViewById(R.id.type_spinner)
        val adapter = ArrayAdapter(
            this,
            R.layout.spinner_item,
            ProductCategory.values()
        )
        typeSpnr.adapter = adapter
        typeSpnr.setSelection(adapter.getPosition(ProductCategory.OTHER))
    }

    private fun fridgeSectionCreate() {
        fridgeSwitch = findViewById(R.id.fridge_enabler)
        calendarView = findViewById(R.id.calendar_view)
        calendarView.isClickable = false

        val calendar = Calendar.getInstance()
        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(year, month, dayOfMonth)
            val dateFormat = "dd/MM/yyyy"
            val sdf = SimpleDateFormat(dateFormat, Locale.US)

            calendarView.text = sdf.format(calendar.time)
        }

        calendarView.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog(this,
                    datePickerDialogListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)).show()
            }
        }


        fridgeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            calendarView.isClickable = isChecked
            Log.d("РЕАКЦИЯ СВИЧА", "isClixkable: ${calendarView.isClickable}")
        }
    }
}
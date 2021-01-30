package ru.mperika.smartshoppinglist.ui.edit

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.smartshoppinglist.R
import ru.mperika.smartshoppinglist.data.ProductCategory
import java.text.SimpleDateFormat
import java.util.*

class ProductEditActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_edit)

        val imageView = findViewById<ImageView>(R.id.imageView)
        val nameText = findViewById<TextView>(R.id.name_text)
        val brandText = findViewById<TextView>(R.id.brand_text)
        val quantityFld = findViewById<TextView>(R.id.quantity_fld)
        createTypeSpinner()

        fridgeSectionCreate()
        val saveBtn = findViewById<Button>(R.id.save_button)
        val cancelBtn = findViewById<Button>(R.id.cancel_button1)
    }

    private fun createTypeSpinner() {
        val typeSpnr = findViewById<Spinner>(R.id.type_spinner)
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            ProductCategory.values()
        )
        typeSpnr.adapter = adapter
        typeSpnr.setSelection(adapter.getPosition(ProductCategory.OTHER))
    }

    private fun fridgeSectionCreate() {
        val fridgeSwitch = findViewById<Switch>(R.id.fridge_enabler)
        val calendarView = findViewById<TextView>(R.id.calendar_view)

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
            if (isChecked) {
                Log.d("СЛУШАТЕЛЬ ПЕРЕКЛЮЧАТЕЛЯ", "Активировано")
                calendarView.isClickable = true
            } else {
                Log.d("СЛУШАТЕЛЬ ПЕРЕКЛЮЧАТЕЛЯ", "Деактивировано")
                calendarView.isClickable = false
            }
        }
    }
}
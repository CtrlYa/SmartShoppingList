package ru.mperika.smartshoppinglist.ui.edit

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.smartshoppinglist.R
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import ru.mperika.smartshoppinglist.ActivityCodes
import ru.mperika.smartshoppinglist.data.Product
import ru.mperika.smartshoppinglist.data.ProductCategory
import ru.mperika.smartshoppinglist.db.AppDatabase
import ru.mperika.smartshoppinglist.ui.main.fridge.EditStatus
import java.text.SimpleDateFormat
import java.util.*

class ProductEditActivity : AppCompatActivity() {
    private var requestCode: ActivityCodes = ActivityCodes.CREATE
    private lateinit var editModelFactory: EditViewModelFactory
    private lateinit var editModel: EditViewModel

    private lateinit var product: Product

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
        editModelFactory = EditViewModelFactory(application)
        editModel = ViewModelProvider(this, editModelFactory).get(EditViewModel::class.java)
        editModel.data.observe(this, {
            //TODO: хз че сюда писать
            when(it) {
                EditStatus.SUCCESS -> {
                    val intent = Intent()
                    intent.putExtra("RESULT", EditStatus.SUCCESS)
                    setResult(Activity.RESULT_OK, intent)
                    finish()}
                EditStatus.FAILURE -> {
                    Toast.makeText(this, "Не удалось внести в базу", Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        })

//        requestCode = intent.extras?.get("open_type") as ActivityCodes

        imageView = findViewById(R.id.imageView)
        nameText = findViewById(R.id.name_text)
        brandText = findViewById(R.id.brand_text)
        quantityFld = findViewById(R.id.quantity_fld)
        quantityFld.text = "0"
        createTypeSpinner()

        fridgeSectionCreate()
        typeSpnr.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?,
                position: Int, id: Long
            ) {

                fridgeSwitch.isChecked = typeSpnr.selectedItem == ProductCategory.MEAL_WITH_EXP_DATE
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        saveBtn = findViewById(R.id.save_button)
        saveBtn.setOnClickListener {
            val product = createProduct()
            Log.d("PRODUCT CREATE VIEW", "Создан продукт: $product")
            editModel.insertProduct(product)
        }
        cancelBtn = findViewById(R.id.cancel_button1)
        cancelBtn.setOnClickListener { finish() }
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
        val datePickerDialogListener =
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = "dd.MM.yyyy"
                val sdf = SimpleDateFormat(dateFormat, Locale.US)

                calendarView.text = sdf.format(calendar.time)
            }

        calendarView.setOnClickListener {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                DatePickerDialog(
                    this,
                    datePickerDialogListener,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }


        fridgeSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            calendarView.isClickable = isChecked
            Log.d("РЕАКЦИЯ СВИЧА", "isClixkable: ${calendarView.isClickable}")
        }
    }

    private fun createProduct(): Product {
        return Product(
            nameText.text.toString(),
            brandText.text.toString(),
            typeSpnr.selectedItem as ProductCategory,
            quantityFld.text.toString().toInt(),
            SimpleDateFormat("dd.MM.yyyy").parse(calendarView.text.let {
                if (it == null || it.isEmpty()) {
                    "01.01.1970" //TODO: Придумать что-то получше
                } else {
                    it.toString()
                }
            }),
            ""
        )
    }
}
package cn.edu.sicnu.cs.tsy.uimvc

import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cn.edu.sicnu.cs.tsy.uimvc.databinding.ActivityMainBinding
import cn.edu.sicnu.cs.tsy.uimvc.model.ProgramAdviser

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adviser: ProgramAdviser by lazy {
        val options = resources.getStringArray(R.array.advice_options)
        val languages = resources.getStringArray(R.array.advice_languages)
        ProgramAdviser(options.zip(languages).toMap())
    }

    private var dynamicTextCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddText.setOnClickListener { addDynamicTextView() }
        binding.btnConfirm.setOnClickListener { showAdviserResult() }
    }

    /** 功能区一：用代码动态创建 TextView，并添加到 ScrollView 内的 LinearLayout 中。 */
    private fun addDynamicTextView() {
        dynamicTextCount++
        val padding = resources.getDimensionPixelSize(R.dimen.dynamic_item_padding)
        val textView = TextView(this).apply {
            text = getString(R.string.area1_item_text, dynamicTextCount)
            setPadding(padding, padding, padding, padding)
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                resources.getDimension(R.dimen.dynamic_item_text_size),
            )
        }
        binding.containerDynamic.addView(textView)
    }

    /** 功能区二：读取下拉框选项 -> 查询 Model 层 -> 在下方显示对应的语言。 */
    private fun showAdviserResult() {
        val option = binding.spinnerOption.selectedItem?.toString().orEmpty()
        binding.tvAdviceResult.text = adviser.getAdvice(option)
    }
}
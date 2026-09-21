package cn.edu.sicnu.cs.sjh.first_work

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 根布局 ConstraintLayout（代替xml根标签）
        val rootLayout = ConstraintLayout(this)
        rootLayout.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        // 1. 创建ImageView（国旗图片）
        val imageView = ImageView(this)
        imageView.id = View.generateViewId()
        imageView.setImageResource(R.drawable.flag)
        imageView.contentDescription = null

        // 2. 创建TextView
        val textView = TextView(this)
        textView.id = View.generateViewId()
        textView.text = resources.getString(R.string.textView)
        textView.textSize = 34f

        // 3. 创建Button
        val button = Button(this)
        button.id = View.generateViewId()
        button.text = resources.getString(R.string.button)

        // 你的点击事件（完全保留原来逻辑）
        button.setOnClickListener {
            textView.text = resources.getString(R.string.clicked)
        }

        // 把控件添加进根布局
        rootLayout.addView(imageView)
        rootLayout.addView(textView)
        rootLayout.addView(button)

        // 设置约束（对应xml里app:layout_constraint）
        val constraintSet = ConstraintSet()
        constraintSet.clone(rootLayout)

        // ImageView：顶部贴父布局，水平居中
        constraintSet.connect(imageView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(imageView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(imageView.id, ConstraintSet.TOP, ConstraintSet.PARENT_ID, ConstraintSet.TOP)

        // TextView：在ImageView下方，水平居中
        constraintSet.connect(textView.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(textView.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(textView.id, ConstraintSet.TOP, imageView.id, ConstraintSet.BOTTOM)

        // Button：在TextView下方，水平居中
        constraintSet.connect(button.id, ConstraintSet.START, ConstraintSet.PARENT_ID, ConstraintSet.START)
        constraintSet.connect(button.id, ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END)
        constraintSet.connect(button.id, ConstraintSet.TOP, textView.id, ConstraintSet.BOTTOM)

        constraintSet.applyTo(rootLayout)

        // 加载代码生成的布局，不再加载xml
        setContentView(rootLayout)
    }
}

package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.autoecole.databinding.ActivityQuizBinding

class QuizCEActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var rightAnswer: String? =null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT=13

    private  val quizData = mutableListOf(
        mutableListOf("ماهي رخصة السياقة صنف ج ؟", "رخصة لقيادة المركبات التي يتعدى وزنها 3500 كلغ", "رخصة لقيادة المركبات التي وزنها 2000 كلغ", "رخصة لقيادة المركبات التي وزنها 1500 كلغ", "رخصة لقيادة المركبات التي وزنها 3000 كلغ"),
        mutableListOf("ما هي الاحتياطات الواجب إتخاذها لنزول منحدر شديد ؟", "أخفض من سرعتي ", "أزيد من السرعة", "أشعل الأضواء", "أشعل الغمازات"),
        mutableListOf("ما هي فترات الزيارة الطبية لسائق الصنف ج ؟", " 60 سنة فما فوق", " 70 سنة فما فوق", " 30 سنة فما فوق", " 65 سنة فما فوق"),
        mutableListOf("ما هي الاضواء الواجب توفرها في الصنف  ج ؟", "ضوء الوضعية", "ضوء الغماز", "ضوء لوحة", "ضوء المؤشر"),
        mutableListOf("إبتداء من أي إرتفاع للمركبة أو الحمولة الموضوعة عليها يجب مالحظة إشارات خاصة وما هي ؟"," إبتداء من 4 م ", "اقل من 4 م ", " 2 م ", " 3 م "),
        mutableListOf("أي منها هي المراقبات الدائمة والدورية الواجب القيام بها قبل كل إقلاع ؟", " حملي لمثلث العطل ", "التأكد من الاطارات", " التأكد من سلامة المركبة ", "التأكد من الوقود"),
        mutableListOf("ماذا تفعل عند الاقتراب من ملتقى الطرق؟", " RASVO أطبق قانون ", " KASVO أطبق قانون ", " MAKSO أطبق قانون ", " TAS أطبق قانون "),
        mutableListOf("أي منها هي المركبات الأولوية", " الشرطة ", " شاحنة", " سيارة اجرة ", "الاسعاف"),
        mutableListOf("ماهي الممنوعات في الطريق السريع", " لوقوف والتوقف ", " السياقة في حالة سكر ", " ممنوع منبه الصوت ", "التجاوز من اليسار"),
        mutableListOf("أي منها هي حالة التجاوز على اليمين؟", " السائق الأمامي ينوي الدوران إلى اليسار ", " الوقوف والتوقف ", " السير في الاتجاه المعاكس", " قص الشريط الخطية "),
        mutableListOf("تعريف الوقوف ", " ترك السيارة لمدة طويلة مع إيقاف المحرك ", " ترك السيارة لمدة قصيرة مع إيقاف المحرك ", " ترك السيارة لمدة قصيرة مع إشغال المحرك", " ترك السيارة لمدة طويلة مع إشغال المحرك "),
        mutableListOf("أي من هذه من أخطار السياقة في الليل؟", " الرؤية الرديئة", " الضباب ", "الانزلاق", "خروج حبواتات"),
        mutableListOf("ماهي حالات حق اليمين واليسار؟", " الخروج من الطريق الريفي ", " الخروج من المدينة ", " الخروج من طريق السيار", " الخروج من الطريق جبلي")

        )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_test)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        quizData.shuffle()
        showNextQuiz()
    }

    fun showNextQuiz(){
        binding.countlabel.text = getString(R.string.count_label, quizCount)
        val quiz = quizData[0]
        rightAnswer=quiz[1]
        binding.questionlabel.text=quiz[0]
        quiz.removeAt(0)
        quiz.shuffle()


        binding.answerBtn1.text=quiz[0]
        binding.answerBtn2.text=quiz[1]
        binding.answerBtn3.text=quiz[2]
        binding.answerBtn4.text=quiz[3]

        quizData.removeAt(0)

    }
    fun checkAnswer(view: View){
        val answerBtn : Button = findViewById(view.id)
        val btnText = answerBtn.text.toString()

        val alertTitle :String
        if(btnText==rightAnswer){
            alertTitle="اجابة صحيحة"
            rightAnswerCount++
        }
        else{
            alertTitle="اجابة خاطئة"
        }
        AlertDialog.Builder(this)
            .setTitle(alertTitle)
            .setMessage("الجواب الصحيح:$rightAnswer")
            .setPositiveButton("حسنا"){ dialogInterface,i->
                checkQuizCount()}
            .setCancelable(false)
            .show()
    }

    fun checkQuizCount(){
        if(quizCount == QUIZ_COUNT  )
        {
            val resultText = "تحصلت على $rightAnswerCount من ${quizCount}"
            AlertDialog.Builder(this)
                .setTitle("النتيجة")
                .setMessage(resultText)
                .setPositiveButton("حسنا") { _, _ -> finish() }
                .setCancelable(false)
                .show()
        }
        else{
            quizCount++
            showNextQuiz()
        }
    }
}
package com.example.autoecole

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.autoecole.databinding.ActivityQuizBinding

class QuizDActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var rightAnswer: String? =null
    private var rightAnswerCount = 0
    private var quizCount = 1
    private val QUIZ_COUNT=14

    private  val quizData = mutableListOf(
        mutableListOf("ما هي فئة السيارات د؟", "مركبات نقل الأشخاص والتي تشمل على أكثر من 8 مقاعد ", "مركبات نقل الأشخاص والتي تشمل على أكثر من 2 مقاعد ", "مركبات نقل الأشخاص والتي تشمل على أكثر من 6 مقاعد ", "مركبات نقل الأشخاص والتي تشمل على أكثر من 4 مقاعد "),
        mutableListOf("هل يُسمح بالحمولة الزائدة على السيارة د؟ ", " لا يُسمح بالحمولة الزائدة", " يُسمح بالحمولة الزائدة", "خارج المدن", "في الطوارئ"),
        mutableListOf("ما هي الإجراءات الازمة في حالة حدوث طوارئ ألي مركبة نقل؟", " تجهيز السيارة بطفاية حريق وصندوق إسعافات أولية ", "طلب الاسعاف", "الإسراع في الانقاض", "طلب مصباح كهربائي"),
        mutableListOf("ما هي المتطلبات التي يجب مراعاتها لنقل الأطفال؟", "التأكد من جلوس الأطفال", "التأكد من حمل رخصة السياقة", "اشعال الغمازات", "ااتصال بالشرطة"),
        mutableListOf("ما هي موقع وفوائد صندوق الإسعافات الأولية للطوارئ؟", " مرئيًا وسهل الوصول إليه للمسافرين ", " يحمل صليبًا أخضر", " غير مغلق", " حالة الإصابات"),
        mutableListOf("ما هو وقت القيادة اليومي والمستمر؟", " 9 ساعات صبا ًح ", " 11 ساعات صبا ًح ", " 10 ساعات صبا ًح ", " 8 ساعات صبا ًح "),
        mutableListOf("ما هي الفحوص المتكررة التي يجب على أي سائق إجراؤها؟", " للفرامل والتوجيه والملحقات ", "المحرك", "الاضواء", "خزان الوقود"),
        mutableListOf("ماذا تفعل عند الاقتراب من ملتقى الطرق؟", " RASVO أطبق قانون ", " KASVO أطبق قانون ", " MAKSO أطبق قانون ", " TAS أطبق قانون "),
        mutableListOf("أي منها هي المركبات الأولوية ", " الشرطة", "شاحنة", "الاسعاف", " سيارة اجرة "),
        mutableListOf("ماهي الممنوعات في الطريق السريع", " لوقوف والتوقف ", " السياقة في حالة سكر ", " ممنوع منبه الصوت", "التجاوز من اليسار"),
        mutableListOf("ماهي حالات التجاوز على اليمين؟", " السائق الأمامي ينوي الدوران إلى اليسار", " الوقوف والتوقف", " السير في الاتجاه المعاكس", " قص الشريط الخطية"),
        mutableListOf("تعريف الوقوف ", " ترك السيارة لمدة طويلة مع إيقاف المحرك ", " ترك السيارة لمدة قصيرة مع إيقاف المحرك ", " ترك السيارة لمدة قصيرة مع إشغال المحرك", " ترك السيارة لمدة طويلة مع إشغال المحرك "),
        mutableListOf("أي من هذه من أخطار السياقة في الليل؟", " الرؤية الرديئة", " الضباب ", "الانزلاق", "خروج حبواتات"),
        mutableListOf("أي من هذه من حالت حق اليمين واليسار؟", " الخروج من الطريق الريفي ", " الخروج من المدينة ", " الخروج من طريق السيار  ", " الخروج من الطريق جبلي "),


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
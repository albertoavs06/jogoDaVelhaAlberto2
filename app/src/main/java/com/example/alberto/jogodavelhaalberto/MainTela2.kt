package com.example.alberto.jogodavelhaalberto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_tela2.*

class MainTela2 : AppCompatActivity() {


    // CONST. DA TAG DE CADA BOTAO
    //UTILIZAMOS ESSA CONST. PARA RECUPERAR O BOTAO ATRAVES DO METODO getQuadrado

    private val QUADRADO = "quadrado"
    // const. que vai ser impressa no text botao
    private val BOLA = "O"
    // const. que var ser impressa no text botao
    private val XIS = "X"

    var lastPlay = "X"

    var view: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tela2)
        val argumentos:Bundle=intent.extras
        view=tela01
        val jogador1:String=argumentos.getString("jogador1")
        val jogador2:String=argumentos.getString("jogador2")

        textView.text=" $jogador1"
        textView2.text=" $jogador2"
    }

    internal var estadoFinal = arrayOf(

            // verificamos as linhas
            intArrayOf(1, 2, 3), //1
            intArrayOf(4, 5, 6), //2
            intArrayOf(7, 8, 9), //3

            // verificamos as colunas
            intArrayOf(1, 4, 7), //4
            intArrayOf(2, 5, 8), //5
            intArrayOf(3, 6, 9), //6

            // as diagonais
            intArrayOf(1, 5, 9), //7
            intArrayOf(3, 5, 7))//8

    // pecorre todas as condicoes definidas na matriz
    // verifica se s1, s2 e s3 sao diferentes de vazio. senao o jogo acaba na primeira jogada. Pois os textos sao iguais.
    // if s1 == s2 && s1 == s3
    val isFim: Boolean
        get() {
            for (x in 0..7) {
                val s1 = getQuadrado(estadoFinal[x][0])!!.text.toString()
                val s2 = getQuadrado(estadoFinal[x][1])!!.text.toString()
                val s3 = getQuadrado(estadoFinal[x][2])!!.text.toString()
                if (s1 != "" &&
                        s2 != "" &&
                        s3 != "") {
                    if (s1 == s2 && s2 == s3) {
                        setColorQuadrados(estadoFinal[x][0], R.color.red)
                        setColorQuadrados(estadoFinal[x][1], R.color.red)
                        setColorQuadrados(estadoFinal[x][2], R.color.red)
                        Toast.makeText(view!!.context, "Fim de Jogo", Toast.LENGTH_LONG).show()

                        return true

                    }
                }
            }
            return false

        }




    fun clickQuadrado(v: View) {

        if (!isFim) {

            if ((v as Button).text == "") {
                if (lastPlay == XIS) {
                    v.text = BOLA
                    lastPlay = BOLA
                } else {
                    v.text = XIS
                    lastPlay = XIS
                }
            } else {
                // imprime mensagem dizendo que nessa posicao ja foi jogado
                Toast.makeText(view!!.context, "Opa! Escolha outro quadrado.", Toast.LENGTH_LONG).show()
            }
        }
        isFim// verifica se e o fim do jogo

    }


    fun getQuadrado(tagNum: Int): Button? {
        // retorna o respectivo quadrado requerido pela variavel tagNum
        return view!!.findViewWithTag<View>(QUADRADO + tagNum.toString()) as Button

    }

    fun newGame(v: View) {

        setEnableQuadrado(true) // ativa os quadrados
        setColorBlack() // pinta os quadrados de preto
        limpaCampos()// invoca o metodo que limpa os campos


        val rX = view!!.findViewById<View>(R.id.rbX) as RadioButton
        // retorna a instancia do nosso radioButton rbX
        // e armazena a inst. na variavel rx

        val rO = view!!.findViewById<View>(R.id.rbO) as RadioButton

        if (rX.isChecked) { // verifica se rX esta checado
            lastPlay = BOLA
            // alteramos o lastPlay para o inverso do que o usuario esta querendo jogar
            // fazemos porque sempre jogamos o oposto que estiver contido em lastPlay
        } else {
            if (rO.isChecked) {
                lastPlay = XIS
                // indicamos que o ultimo a jogar foi o XIS porque queremos comecar a jogar com a bola
            }
        }
    }

    fun setColorQuadrados(btn: Int, colorX: Int) {
        // recebe o num. do botao pela var btn
        // passa a instancia da nossa cor pela variavel colorX
        getQuadrado(btn)!!.setTextColor(resources.getColor(colorX))
        // recuperamos o botao e setamos o textColor com a cor passada pela variavel colorX
    }

    fun limpaCampos() {
        for (i in 1..9) { // percorre todos os botoes
            if (getQuadrado(i) != null) { // verifica se o botao ta recuperando ele e diferente de null
                getQuadrado(i)!!.text = "" // seta o texto como vazio, limpa campos.
            }
        }
    }

    fun setEnableQuadrado(b: Boolean) {
        for (i in 1..9) { // percorrendo todos os itens
            if (getQuadrado(i) != null) { // verifica se quadrado != null
                getQuadrado(i)!!.isEnabled = b // se for, passa o valor de para o respectivo botao
            }
        }
    }

    fun setColorBlack() {
        for (i in 1..9) {
            if (getQuadrado(i) != null) {
                setColorQuadrados(i, R.color.black)
            }
        }

    }
}

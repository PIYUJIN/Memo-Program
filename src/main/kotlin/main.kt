import java.io.*
import java.util.*

//예상 가능한 상태들을 enum class로 정의한다. -> 로그인, 메인 메뉴, 1번 메모 카테고리 관리, 2번 메모 카테고리 선택, 3번 메모 내용 전체 보기, 4번 종료
//정의한 상태별로 while 문 내부를 분기한다.
//각 상태에 대한 클래스들을 정의한다.
//각 상태에서의 입출력 부분을 모두 구현한다.
//구현된 화면을 보고 저장할 데이터들을 선별한다. -> 로그인(비밀번호), 카테고리, 메모 제목, 메모 내용
//데이터 저장 관련 기능을 구현하면서 프로그램과 연동시킨다.


//1. 프로그램 실행시 설정된 비밀번호가 없다면 비밀번호를 설정하는 기능이 먼저 나와야 한다.
//2. 설정된 비밀번호가 있다면 로그인 화면이 나온다.
//3. 제목과 내용은 띄어쓰기 입력을 허용한다.
//4. 모든 입력에 대해 잘못된 입력(허용하지 않는 숫자나 문자열 입력)에 대해 잘못된 입력이라는 문구를 보여줘야 한다.
//5. 카테고리나 메모가 등록된게 없을 경우 카테고리나 메모 목록을 보여주는 화면에서는 등록된 것이 없다는 메시지를 보여줘야 한다.
//6. 비밀번호, 카테고리 정보, 메모 내용 정보 등은 모두 파일에 저장을 하고 파일에서 불러와 보여주는 것으로 처리 한다.



fun main() {
    val mainClass = MainClass()
    mainClass.running()
}

// 파일 목록을 담을 리스트
var categoryFileList = mutableListOf<String>()
// 메모 목록을 담을 리스트
var inputCategoryMemoList = mutableListOf<Memo>()

var selectedCategory = ""

class MainClass {

    val scanner = Scanner(System.`in`)

    // 각 상태별 객체를 생성한다.
    val mainMenuClass = MainMenuClass(scanner)
    val registerClass = RegisterClass(scanner)
    val manageCategoryClass = ManageCategoryClass(scanner)
    val selectCategoryClass = SelectCategoryClass(scanner)

    // 프로그램 상태를 담는 변수에 초기 상태를 설정
    var programState = ProgramState.PROGRAM_STATE_REGISTER_PASSWORD
//    var programState = ProgramState.PROGRAM_STATE_SHOW_MENU

    fun running() {
        while (true) {
            // 프로그램 상태에 따른 분기
            when (programState) {
                // 로그인
                ProgramState.PROGRAM_STATE_REGISTER_PASSWORD -> {
                    registerClass.register()
                    programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                }
                // 메인 메뉴
                ProgramState.PROGRAM_STATE_SHOW_MENU -> {
                    // 메인 메뉴를 보여준다.
                    val inputMainMenuNumber = mainMenuClass.inputMainMenuNumber()
                    when(inputMainMenuNumber) {

                        MainMenuItem.MAIN_MENU_ITEM_MANAGE_CATEGORY.itemNumber -> {
                            programState = ProgramState.PROGRAM_STATE_CATEGORY
                        }
                        MainMenuItem.MAIN_MENU_ITEM_SELECT_CATEGORY.itemNumber -> {
                            programState = ProgramState.PROGRAM_STATE_SELECT_CATEGORY
                        }
                        MainMenuItem.MAIN_MENU_ITEM_PRINT_ALL.itemNumber -> {
                            programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                        }
                        MainMenuItem.MAIN_MENU_ITEM_EXIT.itemNumber -> {
                            programState = ProgramState.PROGRAM_STATE_EXIT
                        }
                    }
                }
                // 1번 메모 카테고리 관리
                ProgramState.PROGRAM_STATE_CATEGORY -> {
                    manageCategoryClass.manageCategory()
                    programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                }
                // 2번 메모 카테고리 선택
                ProgramState.PROGRAM_STATE_SELECT_CATEGORY -> {
                    selectCategoryClass.selectCategory()
                    programState = ProgramState.PROGRAM_STATE_SHOW_MENU
                }
                // 3번 메모 내용 전체 보기
                ProgramState.PROGRAM_STATE_SHOW_MEMO -> {}
                // 4번 종료
                ProgramState.PROGRAM_STATE_EXIT -> {
                    break
                }
            }
        }
    }
}

// 파일 목록을 불러온다.
fun readCategoryFile() {
    // 파일 목록 리스트를 초기한다.
    categoryFileList.clear()

    // 현재 위치의 파일 목록을 가져온다.
    val dir = File(".")
    var fileList = dir.list()

    println()
    // 파일 목록에서 .record로 끝나는 것들만 담아 준다.
    for(file in fileList){
        if(file.endsWith(".record")){
            val renameFile = file.replace(".record", "")
            categoryFileList.add(renameFile)
        }
    }
}

fun readMemo() {
    try {
        inputCategoryMemoList.clear()

        val fis = FileInputStream("${selectedCategory}.record")
        val ois = ObjectInputStream(fis)

        for (memo in 0 until fis.available()) {
            inputCategoryMemoList.add(ois.readObject() as Memo)
        }

        ois.close()
        fis.close()
    } catch(e: EOFException) {
        return
    }
}

// 카테고리 리스트 출력
fun printCategory(){
    // 등록된 카테고리 목록 출력
    if(categoryFileList.size == 0){
        println("등록된 카테고리가 없습니다.")
    } else {
        for (idx in 1..categoryFileList.size) {
            println("$idx : ${categoryFileList[idx - 1]}")
        }
    }
}

data class Memo(var categoryName:String, var memoName:String, var memoContent:String) : Serializable

enum class ProgramState {
    PROGRAM_STATE_REGISTER_PASSWORD,
    PROGRAM_STATE_SHOW_MENU,
    PROGRAM_STATE_CATEGORY,
    PROGRAM_STATE_SELECT_CATEGORY,
    PROGRAM_STATE_SHOW_MEMO,
    PROGRAM_STATE_EXIT
}
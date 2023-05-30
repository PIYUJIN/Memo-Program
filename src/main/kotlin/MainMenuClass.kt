import java.util.*

class MainMenuClass(var scanner: Scanner) {
    // 메인 메뉴를 보여주고 메뉴 번호를 입력받는다.
    fun inputMainMenuNumber() : Int{
        while(true) {
            try {
                println()
                println("1. 메모 카테고리 관리")
                println("2. 메모 카테고리 선택")
                println("3. 메모 내용 전체 보기")
                println("4. 종료")
                print("메뉴를 선택해주세요 : ")
                val inputNumberTemp = scanner.next()
                val inputNumber = inputNumberTemp.toInt()

                if (inputNumber !in 1..4) {
                    println("잘못 입력 하였습니다. 메뉴에 해당하는 숫자를 입력해주세요.")
                } else {
                    return inputNumber
                }
            } catch(e:Exception){
                println("잘못 입력 하였습니다. 숫자로 입력해주세요.")
            }

        }
    }
}

// 메인 메뉴 항목
public enum class MainMenuItem(val itemNumber:Int){
    // 메모 카테고리 관리
    MAIN_MENU_ITEM_MANAGE_CATEGORY(1),
    // 메모 카테고리 선택
    MAIN_MENU_ITEM_SELECT_CATEGORY(2),
    // 메모 내용 전체 보기
    MAIN_MENU_ITEM_PRINT_ALL(3),
    // 종료
    MAIN_MENU_ITEM_EXIT(4)
}

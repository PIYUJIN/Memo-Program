import java.util.*

class SelectCategoryClass(var scanner: Scanner) {
    // 1.메모보기, 2. 메모등록, 3. 메모수정, 4.메모삭제, 5.이전 :

    var inputCategoryNum = 0
    var inputCategoryMemoList = mutableListOf<Memo>()

    fun selectCategory() {
        while(true) {
            println()
            readCategoryFile()
            printCategory()
            println()
            print("선택할 카테고리 번호를 입력해주세요 (0.이전) : ")
            var inputCategoryNumTemp = scanner.next()
            inputCategoryNum = inputCategoryNumTemp.toInt()
            scanner.nextLine()
            if (inputCategoryNum == 0) {
                break
            } else {
                println()
                printMemo()
                println()
                print("1.메모보기, 2. 메모등록, 3. 메모수정, 4.메모삭제, 5.이전 : ")
                var inputMenuNumTemp = scanner.next()
                var inputMenuNum = inputMenuNumTemp.toInt()
                scanner.nextLine()


                when (inputMenuNum) {
                    menuTwoItem.MENU_TWO_PRINT_MEMO.itemNumber -> {
                        printMemoInfo()
                        continue
                    }

                    menuTwoItem.MENU_TWO_WRITE_MEMO.itemNumber -> {

                    }

                    menuTwoItem.MENU_TWO_EDIT_MEMO.itemNumber -> {

                    }

                    menuTwoItem.MENU_TWO_DELETE_MEMO.itemNumber -> {

                    }

                    menuTwoItem.MENU_TWO_EXIT.itemNumber -> {
                        break
                    }
                }
            }
        }
    }

    fun printMemo() {

        for (category in memoList) {
            if (category.categoryName == categoryFileList[inputCategoryNum - 1]) {
                inputCategoryMemoList.add(memoList[inputCategoryNum - 1])
            }
        }
        // 등록된 메모 목록 출력
        if(inputCategoryMemoList.size == 0){
            println("등록된 메모가 없습니다.")
        } else {
            for (idx in 1..inputCategoryMemoList.size) {
                println("$idx : ${inputCategoryMemoList[idx - 1]}")
            }
        }
    }

    fun printMemoInfo() {
        while(true) {
            println()
            print("확인할 메모의 번호 입력해주세요 (0.이전) : ")
            var inputMemoNumTemp = scanner.next()
            var inputMemoNum = inputMemoNumTemp.toInt()
            scanner.nextLine()

            if(inputCategoryMemoList.size != 0) {
                if (inputMemoNum in 1..inputCategoryMemoList.size) {
                    for (i in 0 until inputCategoryMemoList.size) {
                        println("${inputCategoryMemoList[i].memoName}")
                        println("${inputCategoryMemoList[i].memoContent}")
                    }
                } else if (inputMemoNum == 0) {
                    break
                } else {
                    println()
                    println("다시 입력해주세요. 해당 번호에 해당하는 메모가 없습니다.")
                    continue
                }
            } else {
                println()
                println("해당 카테고리에 저장된 메모가 없습니다.")
                break
            }
        }
    }
}


enum class menuTwoItem(val itemNumber:Int) {
    MENU_TWO_PRINT_MEMO(1),
    MENU_TWO_WRITE_MEMO(2),
    MENU_TWO_EDIT_MEMO(3),
    MENU_TWO_DELETE_MEMO(4),
    MENU_TWO_EXIT(5)
}
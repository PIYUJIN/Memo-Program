import java.lang.Exception

class PrintMemoClass {
    fun printMemo() {
        while(true) {
            try {
                readCategoryFile()
                for (category in categoryFileList) {
                    println()
                    println("----------------------------")
                    println(category)
                    println("----------------------------")
                    readMemo(category)
                    if (inputCategoryMemoList.size == 0) {
                        println("등록된 메모가 없습니다.")
                    } else {
                        for (memo in inputCategoryMemoList) {
                            println()
                            println("제목 : ${memo.memoName}")
                            println("내용 : ${memo.memoContent}")
                        }
                    }
                }
                break
            } catch (e: Exception) {
                println()
                println("잘못된 형식입니다. 다시입력해주세요.")
                continue
            }
        }
    }
}
class PrintMemoClass {
    fun printMemo() {
        readCategoryFile()
        for (category in categoryFileList) {
            println()
            println("----------------------------")
            println(category)
            println("----------------------------")
            readMemo(category)
            if(inputCategoryMemoList.size==0) {
                println("등록된 메모가 없습니다.")
            } else {
                for (memo in inputCategoryMemoList) {
                    println()
                    println("제목 : ${memo.memoName}")
                    println("내용 : ${memo.memoContent}")
                }
            }
        }
    }
}
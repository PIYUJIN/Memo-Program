import java.io.FileOutputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import java.util.*

class SelectCategoryClass(var scanner: Scanner) {
    // 1.메모보기, 2. 메모등록, 3. 메모수정, 4.메모삭제, 5.이전 :

    var inputCategoryNum = 0

    fun selectCategory() {
        readCategoryFile()
        printCategory()
        while(true) {
            try {
                println()
                print("선택할 카테고리 번호를 입력해주세요 (0.이전) : ")
                var inputCategoryNumTemp = scanner.next()
                inputCategoryNum = inputCategoryNumTemp.toInt()
                scanner.nextLine()

//                println(categoryFileList.size)
                if(inputCategoryNum in 1..categoryFileList.size) {
                    selectedCategory = categoryFileList[inputCategoryNum - 1]
                    readMemo()
                    break
                }
                else {
                    println()
                    println("일치하는 범위의 카테고리가 없습니다. 다시 입력해주세요.")
                    continue
                }
            } catch (e: Exception) {
//                e.printStackTrace()
                println()
                println("잘못된 형식입니다. 다시 입력해주세요.")
                continue
            }
        }

            if (inputCategoryNum == 0) {
                return
            } else {
                while (true) {
                    try {
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
                                writeMemo()
                                continue
                            }

                            menuTwoItem.MENU_TWO_EDIT_MEMO.itemNumber -> {
                                editMemo()
                                continue
                            }

                            menuTwoItem.MENU_TWO_DELETE_MEMO.itemNumber -> {
                                deleteMemo()
                                continue
                            }

                            menuTwoItem.MENU_TWO_EXIT.itemNumber -> {
                                writeMemoFile()
                                break
                            }
                        }
                    } catch(e: Exception) {
                        println("잘못된 형식입니다. 다시입력해주세요.")
                        continue
                    }
            }
        }
    }

    fun printMemo() {
        // 등록된 메모 목록 출력
        println()
        if(inputCategoryMemoList.size == 0){
            println("등록된 메모가 없습니다.")
        } else {
            for (idx in 1..inputCategoryMemoList.size) {
                println("$idx : ${inputCategoryMemoList[idx - 1].memoName}")
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
                    println()
                    println("제목 : ${inputCategoryMemoList[inputMemoNum-1].memoName}")
                    println("내용 : ${inputCategoryMemoList[inputMemoNum-1].memoContent}")
                    do {
                        print("이전으로 돌아가려면 0을 입력하세요 : ")
                        var inputReturnTemp = scanner.next()
                        var inputReturn = inputReturnTemp.toInt()
                    } while(inputReturn!=0)
                    break
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

    fun writeMemo() {
        print("메모 제목 : ")
        var memoName = scanner.nextLine()
        print("메모 내용 : ")
        var memoContent = scanner.nextLine()
        inputCategoryMemoList.add(Memo(selectedCategory,memoName,memoContent))
    }

    fun editMemo() {
        while(true) {
            print("수정할 메모의 번호를 입력해주세요 (0.이전) : ")
            var editNumTemp = scanner.next()
            var editNum = editNumTemp.toInt()
            scanner.nextLine()
            println()
            if (editNum in 1..inputCategoryMemoList.size) {
                println("제목 : ${inputCategoryMemoList[editNum - 1].memoName}")
                print("메모의 새로운 제목을 입력해주세요 (0 입력시 무시합니다) : ")
                var editMemoName = scanner.nextLine()
                if (editMemoName != "0") {
                    inputCategoryMemoList[editNum - 1].memoName = editMemoName
                } else {
                }
                println("내용 : ${inputCategoryMemoList[editNum - 1].memoContent}")
                print("메모의 새로운 내용을 입력해주세요 (0 입력시 무시합니다) : ")
                var editMemoContent = scanner.nextLine()
                if (editMemoContent != "0") {
                    inputCategoryMemoList[editNum - 1].memoContent = editMemoContent
                } else {

                }
                break
            }
            else if(editNum==0) {
                break
            }
            else {
                println("다시 입력해주세요. 해당 번호에 해당하는 메모가 없습니다.")
                continue
            }
        }
    }

    fun deleteMemo() {
        while(true) {
            print("삭제할 메모의 번호를 입력해주세요 (0.이전) : ")
            var deleteNumTemp = scanner.next()
            var deleteNum = deleteNumTemp.toInt()

            if (deleteNum in 1..inputCategoryMemoList.size) {
                inputCategoryMemoList.removeAt(deleteNum - 1)
                break
            }
            else if(deleteNum==0) {
                break
            }
            else {
                println()
                println("다시 입력해주세요. 해당 번호에 해당하는 메모가 없습니다.")
                continue
            }
        }
    }
}


fun writeMemoFile() {
    val fos = FileOutputStream("${selectedCategory}.record")
    val oos = ObjectOutputStream(fos)

    for (memo in inputCategoryMemoList) {
        oos.writeObject(memo)
    }

    oos.flush()
    oos.close()
    fos.close()
}

enum class menuTwoItem(val itemNumber:Int) {
    MENU_TWO_PRINT_MEMO(1),
    MENU_TWO_WRITE_MEMO(2),
    MENU_TWO_EDIT_MEMO(3),
    MENU_TWO_DELETE_MEMO(4),
    MENU_TWO_EXIT(5)
}
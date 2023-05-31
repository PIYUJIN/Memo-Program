import java.io.*
import java.lang.Exception
import java.util.*

class ManageCategoryClass(var scanner: Scanner) {
//1. 카테고리 등록
//2. 카테고리 삭제
//3. 카테고리 수정
//4. 이전
//카테고리 관리 메뉴 선택 :

    lateinit var category :String

    fun manageCategory() {
        while(true) {
            try {
                // 저장된 카테고리 목록 불러오기
                readCategoryFile()
                printCategory()

                println()
                println("1. 카테고리 등록")
                println("2. 카테고리 삭제")
                println("3. 카테고리 수정")
                println("4. 이전")
                print("카테고리 관리 메뉴 선택 : ")
                var inputNumTemp = scanner.next()
                var inputNum = inputNumTemp.toInt()

                when (inputNum) {
                    // 1번 카테고리 등록
                    menuOneItem.MENU_ONE_WRITE_CATEGORY.itemNumber -> {
                        writeCategoryFile(makeCategoryFileName(scanner))
                        continue
                    }

                    // 2번 카테고리 삭제
                    menuOneItem.MENU_ONE_DELETE_CATEGORY.itemNumber -> {
                        deleteCategoryFile()
                        continue
                    }

                    // 3번 카테고리 수정
                    menuOneItem.MENU_ONE_EDIT_CATEGORY.itemNumber -> {
                        editCategoryFileName()
                        continue
                    }

                    // 4번 이전
                    menuOneItem.MENU_ONE_EXIT.itemNumber -> {
                        break
                    }
                }
            } catch(e: Exception) {
                println("잘못입력하였습니다. 숫자로 입력해주세요.")
                continue
            }
        }
    }

    // 카테고리 이름 설정
    fun makeCategoryFileName(scanner: Scanner) : String{
        scanner.nextLine()
        println()
        print("등록할 카테고리 이름을 입력해주세요 : ")
        category = scanner.nextLine()
        return "${category}.record"
    }

    // 1번 카테고리 등록
    fun writeCategoryFile(fileName:String){
        // 카테고리 파일을 생성
        val fos = FileOutputStream(fileName)
        val oos = ObjectOutputStream(fos)

        oos.flush()
        oos.close()
        fos.close()
    }

    // 2번 카테고리 삭제
    fun deleteCategoryFile() {
        while(true) {
            scanner.nextLine()
            println()
            // 삭제할 카테고리 선택
            print("삭제할 카테고리 번호를 입력해주세요 : ")
            var deleteCategoryTemp = scanner.next()
            var deleteCategoryNum = deleteCategoryTemp.toInt()
            if (deleteCategoryNum in 1 ..categoryFileList.size) {
                // 파일 삭제
                var file = File("${categoryFileList[deleteCategoryNum - 1]}.record")
                file.delete()
                break
            }
            else {
                println("숫자에 해당하는 파일이 없습니다. 다시 입력해주세요.")
                continue
            }
        }
    }

    // 3번 카테고리 수정
    fun editCategoryFileName() {
        while (true) {
            scanner.nextLine()
            println()
            // 수정할 카테고리 선택
            print("수정할 카테고리 번호를 입력해주세요 : ")
            var editCategoryTemp = scanner.next()
            var editCategoryNum = editCategoryTemp.toInt()
            if (editCategoryNum in 1 .. categoryFileList.size) {
                print("${categoryFileList[editCategoryNum - 1]} -> ")
                scanner.nextLine()
                var editCategoryName = scanner.nextLine()
                // 파일명 변경
                var file = File("${categoryFileList[editCategoryNum - 1]}.record")
                file.renameTo(File("${editCategoryName}.record"))
                break
            }
            else {
                println("숫자에 해당하는 파일이 없습니다. 다시 입력해주세요.")
                continue
            }
        }
    }
}



enum class menuOneItem(val itemNumber:Int) {
    MENU_ONE_WRITE_CATEGORY(1),
    MENU_ONE_DELETE_CATEGORY(2),
    MENU_ONE_EDIT_CATEGORY(3),
    MENU_ONE_EXIT(4)
}


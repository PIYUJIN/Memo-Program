import java.io.*
import java.lang.Exception
import java.util.*
import kotlin.math.log


lateinit var password : String

class RegisterClass(var scanner: Scanner) {
    fun register() {
        // 회원가입 (비밀번호 설정)
        while(true) {
            try {
                readPassword()
                login()
                break
            }
            catch(e: Exception) {
                print("설정할 비밀번호를 입력해주세요 : ")
                password = scanner.next()
                print("한번 더 입력해주세요 : ")
                var passwordChecking = scanner.next()
                if (password == passwordChecking) {
                    writePassword("password.txt")
                    login()
                    break
                } else {
                    println()
                    println("다시 입력해주세요. 일치하지 않습니다.")
                    println()
                    continue
                }
            }
        }
    }

    // 비밀번호가 저장되어 있는 경우 로그인 화면 실행
    fun login() {
        while(true) {
            println()
            print("로그인하시려면 비밀번호를 입력하세요 : ")
            var inputPassword = scanner.next()
            // 입력된 비밀번호와 저장된 비밀번호 비교
            if (password == inputPassword) {
                return
            } else {
                println()
                println("잘못된 비밀번호입니다. 다시 입력해주세요.")
                continue
            }
        }
    }
}

// 비밀번호 데이터 저장
fun writePassword(fileName:String){

    val fos = FileOutputStream(fileName)
    val dos = DataOutputStream(fos)

    dos.writeUTF(password)

    dos.flush()
    dos.close()
    fos.close()
}

// 비밀번호 데이터 가져오기
fun readPassword() {
    val fis = FileInputStream("password.txt")
    val dis = DataInputStream(fis)

    password = dis.readUTF()
}
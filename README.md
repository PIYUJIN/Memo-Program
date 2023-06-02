# Memo-Program
This is memo management program with Kotlin.

**PROGRAM STATE**
- **PROGRAM_STATE_REGISTER_PASSWORD**
  -  실행 method : **_register()_** in RegisterClass
  
          1. password 등록 기록이 없는 경우
          
          설정할 비밀번호를 입력해주세요 : 
          한번 더 입력해주세요 : 
          
          2. password 등록 기록이 있는 경우
          
          로그인하시려면 비밀번호를 입력하세요 : 
    
  - 생성 파일 : _password.txt_
  - 다음 상태 : **_PROGRAM_STATE_SHOW_MENU_**

- **PROGRAM_STATE_SHOW_MENU**
  -  실행 method : **_register()_** in RegisterClass
  
           1. 메모 카테고리 관리
           2. 메모 카테고리 선택
           3. 메모 내용 전체 보기
           4. 종료
           메뉴를 선택해주세요 :
    
  - 다음 상태
    - 1번 선택한 경우 : **_PROGRAM_STATE_CATEGORY_**
    - 2번 선택한 경우 : **_PROGRAM_STATE_SELECT_CATEGORY_**
    - 3번 선택한 경우 : **_PROGRAM_STATE_PRINT_MEMO_**
    - 4번 선택한 경우 : **_PROGRAM_STATE_EXIT_**

- **PROGRAM_STATE_CATEGORY**
  -  실행 method : **_manageCategory()_** in ManageCategoryClass
  
         1. 등록된 카테고리가 없는 경우 : 등록된 카테고리가 없습니다.
         2. 등록된 카테고리가 있는 경우 : 카테고리 리스트
         
         
         1. 카테고리 등록
         2. 카테고리 삭제
         3. 카테고리 수정
         4. 이전
         카테고리 관리 메뉴 선택 :
    
    - 메뉴별 실행 method 
       - 1번 선택한 경우 : **_writeCategoryFile(makeCategoryFileName())_**
       
                등록할 카테고리 이름을 입력해주세요 : 
                
       - 2번 선택한 경우 : **_deleteCategoryFile()_**
       
                삭제할 카테고리 번호를 입력해주세요 : 
                
       - 3번 선택한 경우 : **_editCategoryFileName()_**
       
                수정할 카테고리 번호를 입력해주세요 : 
                ${선택된 카테고리} -> 
                
       - 4번 선택한 경우 : main menu
  - 생성 파일 : _${category}.record_
  - 다음 상태 : **_PROGRAM_STATE_SHOW_MENU_**

- **PROGRAM_STATE_SELECT_CATEGORY**
  -  실행 method : **_selectCategory()_** in SelectCategoryClass
  
          1. 등록된 카테고리가 없는 경우 : 등록된 카테고리가 없습니다.
          2. 등록된 카테고리가 있는 경우 : 카테고리 리스트
          선택할 카테고리 번호를 입력해주세요 (0.이전) : 
          
          
          1. 등록된 메모가 없는 경우 : 등록된 메모가 없습니다.
          2. 등록된 메모가 있는 경우 : 메모 리스트
          1.메모보기, 2. 메모등록, 3. 메모수정, 4.메모삭제, 5.이전 : 
          
    
   - 메뉴별 실행 method 
     - 1번 선택한 경우 : **_printMemoInfo()_**

              확인할 메모의 번호 입력해주세요 (0.이전) : 
              
              제목 : ${선택된 메모 제목}
              내용 : ${선택된 메모 내용}
              이전으로 돌아가려면 0을 입력하세요 : 
              
     - 2번 선택한 경우 : **_writeMemo()_**

              메모 제목 :
              메모 내용 :

     - 3번 선택한 경우 : **_editMemo()_**

              수정할 메모의 번호를 입력해주세요 (0.이전) : 
              
              제목 : ${선택된 메모 제목}
              메모의 새로운 제목을 입력해주세요 (0 입력시 무시합니다) : 
              내용 : ${선택된 메모 내용}
              메모의 새로운 내용을 입력해주세요 (0 입력시 무시합니다) : 

     - 4번 선택한 경우 : **_deleteMemo()_**
              
              삭제할 메모의 번호를 입력해주세요 (0.이전) : 
              
     - 5번 선택한 경우 : **_writeMemoFile()_** → main menu
 
  - 데이터 저장 파일 : _${selectedCategory}.record_
  - 다음 상태 : **_PROGRAM_STATE_SHOW_MENU_**

- **PROGRAM_STATE_PRINT_MEMO**
  -  실행 method : **_printMemo()_** in PrintMemoClass
  
          ----------------------------
          카테고리
          ----------------------------
          
          1. 등록된 메모가 없는 경우 : 등록된 메모가 없습니다.
          2. 등록된 메모가 있는 경우 : 메모 리스트
              제목 : ${선택된 메모 제목}
              내용 : ${선택된 메모 내용}
    
  - 다음 상태 : **_PROGRAM_STATE_SHOW_MENU_**

- **PROGRAM_STATE_EXIT**
  - 프로그램 종료




## 데이터 관리
- 카테고리 
  - 카테고리별 파일 생성 (파일 이름 : 카테고리명)
  ~~~kotlin
  var categoryFileList = mutableListOf<String>()
  ~~~

- 메모
  - 데이터 클래스로 메모 제목, 내용 관리
  ~~~kotlin
  data class Memo(var categoryName:String, var memoName:String, var memoContent:String) : Serializable
  ~~~
  - 카테고리 파일에 objectStream 사용하여 저장 및 불러오기
  ~~~kotlin
  var inputCategoryMemoList = mutableListOf<Memo>()
  ~~~
  

## 실행 영상
- register

![register](https://github.com/PIYUJIN/Memo-Program/assets/86800087/87996fbf-23d7-4e89-9579-35f930ed1c58)


- main menu 1

![menu1-front](https://github.com/PIYUJIN/Memo-Program/assets/86800087/07b08290-3b4a-4fd4-a441-faf6210fe4a4)
![menu1-back](https://github.com/PIYUJIN/Memo-Program/assets/86800087/35f99bda-fb30-4a85-9ec5-3ac490964895)


- main menu 2

![menu2-front-part](https://github.com/PIYUJIN/Memo-Program/assets/86800087/6231aa65-919e-430c-941a-52b637acca6b)
![menu2-back](https://github.com/PIYUJIN/Memo-Program/assets/86800087/35273751-a9fa-4336-b9c8-f8db48e621d1)

- main menu 3

![menu3](https://github.com/PIYUJIN/Memo-Program/assets/86800087/ff74467c-0123-4bb7-850d-7e018a686b89)

- main menu 4 (종료)

![menu4](https://github.com/PIYUJIN/Memo-Program/assets/86800087/1ba2dec4-4b2f-4a97-9526-1765d4b91850)


 

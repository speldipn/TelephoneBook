# TelephoneBook

#### RecyclerView를 사용하여 전화부 만들기

### 동작시현
![screenshot01](screenshot/screenshot01.png)


#### 필요 권한
* 전화번호부 읽고/쓰기 권한
* 전화걸기 권한

````java
static String[] permissions = {
  Manifest.permission.READ_CONTACTS,
  Manifest.permission.WRITE_CONTACTS,
  Manifest.permission.CALL_PHONE
};
````

#### Contents Resolver
1. 데이터 주소
2. 가져올 데이터의 컬럼 정의
3. 쿼리
4. 결과값 처리

````java
public class Phone {
  public String id;
  public String name;
  public String phone;
}
````

Link: [go source](app/src/main/java/org/androidtown/telephonebook/domain/PhoneDAO.java)

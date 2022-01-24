# 스프링부트 실전 활용 마스터
## Chapter - 2 
### 스프링 부트를 활용한 데이터 엑세스
- 리액티브 MongoDB 활용 - 의존성 추가
```groovy
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    implementation 'de.flapdoodle.embed:de.flapdoodle.embed.mongo'
    implementation 'org.mongodb:mongodb-driver-sync'
```
- 도메인 추가
    - 판매 상품 (Inventory Item)
    - 장바구니 (Cart)
    - 구매 상품 (Item in a Cart)
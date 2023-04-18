## 📚 Book Searching
간단 소개: [네이버 책 검색 API](https://developers.naver.com/docs/serviceapi/search/book/book.md)를 활용한 책 검색 앱
</br>개발 기간: 2023년 4월 14일 ~ 2023년 4월 18일(5일)


## ✅ 요구사항
1. 네이버 책 검색 API를 활용한 책 검색 앱 만들기
2. 검색 기능 및 검색어 저장 기능 구현


## 🎥 구현
[시연 영상](https://drive.google.com/file/d/13EBlqA_zEI4CgDWbTSAf4gUHOtEMecLz/view?usp=sharing)
#### 책 검색 및 페이징
<img width="200" src="https://user-images.githubusercontent.com/70684334/232788592-164f6204-a437-4400-94f1-9eb07f47b83b.gif">

#### 상세 페이지 외부 앱 연동
<img width="200" src="https://user-images.githubusercontent.com/70684334/232789021-7d39d223-26c1-4085-af1a-a7e7a5fdc04e.gif">

#### 최근 검색어 저장
<img width="200" src="https://user-images.githubusercontent.com/70684334/232789467-8772e284-cffd-459b-b3e8-32e979d1f393.gif">

#### 최근 검색어 삭제
<img width="200" src="https://user-images.githubusercontent.com/70684334/232789596-71d00997-dc54-421b-9844-ae1ad67583e1.gif">

## 🛠️ 활용 기술
- Kotlin
- Jetpack AAC(Room, Paging3, ViewModel, ViewBinding, DataBinding, LiveData)
- MVVM, Clean Architecture
- Retrofit2, Okhttp3, Glide, Hilt
- Coroutine, Flow


## 🗂️ 파일 구조
```bash
├── data
│   ├── api
│   │   ├── BookSearchApiClient.kt
│   │   └── BookSearchInterface.kt
│   ├── db
│   │   ├── SearchWordDao.kt
│   │   └── SearchWordDatabase.kt
│   ├── di
│   │   ├── BookRemoteDataSourceModule.kt
│   │   ├── BookSearchApiModule.kt
│   │   ├── BookSearchRepositoryModule.kt
│   │   ├── DataBaseModule.kt
│   │   └── SearchWordRepositoryModule.kt
│   ├── mapper
│   │   └── Mapper.kt
│   ├── model
│   │   ├── BookSearch
│   │   │   └── BookSearchResponse.kt
│   │   └── recentSearch
│   │       └── SearchWordEntity.kt
│   └── repositories
│       ├── BookSearchRepositoryImpl.kt
│       ├── SearchWordRepositoryImpl.kt
│       ├── paging
│       │   └── PagingDataSourceImpl.kt
│       ├── recentSearch
│       │   ├── SearchWordDataSource.kt
│       │   └── SearchWordDataSourceImpl.kt
│       └── search
│           ├── BookRemoteDataSource.kt
│           └── BookRemoteDataSourceImpl.kt
├── domain
│   ├── di
│   │   └── UseCaseModule.kt
│   ├── model
│   │   ├── Book.kt
│   │   └── SearchWordItem.kt
│   ├── repositories
│   │   ├── BookSearchRepository.kt
│   │   └── SearchWordRepository.kt
│   └── usecase
│       ├── DeleteSearchWordUseCase.kt
│       ├── GetAllRecentWordUseCase.kt
│       ├── GetBookListUseCase.kt
│       └── InsertSearchWordUseCase.kt
└── presentation
    ├── base
    │   ├── BaseActivity.kt
    │   └── BaseFragment.kt
    ├── di
    │   └── ApplicationClass.kt
    ├── view
    │   ├── MainActivity.kt
    │   ├── recentSearch
    │   │   ├── BindingAdapter.kt
    │   │   ├── RecentSearchAdapter.kt
    │   │   └── RecentSearchFragment.kt
    │   └── search
    │       ├── BindingAdapter.kt
    │       ├── BookSearchAdapter.kt
    │       └── SearchFragment.kt
    └── viewModels
        └── SearchViewModel.kt
```
## 📚 Book Searching
간단 소개: [네이버 책 검색 API](https://developers.naver.com/docs/serviceapi/search/book/book.md)를 활용한 책 검색 앱
</br>개발 기간: 2023년 4월 14일 ~ 2023년 4월 18일(5일)


## ✅ 요구사항
1. 네이버 책 검색 API를 활용한 책 검색 앱 만들기
2. 검색 기능 및 검색어 저장 기능 구현


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
스프링5 정리
---

#### 메이븐이란?

  - 프로젝트의 빌드, 필요한 의존모듈, 라이프 사이클, 사이트 생성 등 프로젝트 전반을 위한 관리 도구. 
  - 많은 자바 프로젝트가 메이븐을 사용하여 프로젝트를 관리한다.
  - pom.xml 파일에 프로젝트에서 필요로하는 의존 모듈이나 플러그인 등에 대한 설정 정보를 담고 있다. 

#### 그레이들이란? 

  - 메이븐과 유사한 프로젝트 관리도구. pom.xml 파일 대신 build.gradle 파일을 사용한다. 
  - src/main/resources 폴더를 xml이나 프로퍼티파일과 같은 자원파일을 위한 소스폴더로 사용한다. 

#### 컨테이너란?

  - 화물을 운송하는 컨테이너처럼, 조립된 객체들을 모아서 편리하게 쓸 수 있도록 만들어준다. 
  - 객체를 연결하는 정보를 관리 
  - 객체 간 의존성 때문에 일어나는 문제들을 줄여주어 유지 보수에 용이하다
  - AnnotationConfigApplicationContext로 스프링 컨테이너 생성 가능

#### 객체 조립기란? 

  - 의존 객체를 주입하는 코드를 작성하는 클래스. 객체 본드. 
  -  스프링은 Bean을 활용하여 객체를 조립한다. 
  - 이노테이션이란? 
  - 주석이라는 뜻이다. 주석처럼 코드에 달아 특정 클래스에 의미를 부여하거나 기능주입.
  - 대표적인 예 @Override, @Bean, ...  

#### 싱글톤 객체란?
  - 하나의 객체만을 생성하여 계속 이용하는 것이다.
  - spring에서는 별도 설정을 하지 않을 경우 한개의 빈 객체만을 생성한다. 이때 빈 객체는 싱글톤 범위를 갖는다고 한다.  
 
DI(Dependency Injection))란? 
--- 

#### 의존이란?
객체끼리 얽혀있는 것. 즉 얽힌 객체들 중 하나를 수정하면 얽힌 객체 모두가 영향을 받는다 = 코드를 수정할 때 문제 발생확률이 높아진다 = 유지보수가 어렵다. 
#### ID(의존주입)이란?
의존하는 객체를 직접 생성하는 대신 의존객체를 전달받아 사용함으로써 의존으로 인한 문제 발생을 줄이는 것.

#### DI의 방법
 - 생성자를 통해 객체 주입 
 - 새터 매서드를 통해 객체 주입
 - 자바빈 규칙에 따라 setter 매서드를 작성하여야한다. 

#### 생성자 DI vs 새터 매서드 DI 차이
전자는 빈 객체가 생성되는 시점에 객체의 모든 의존 객체를 전달하고, 후자는 새터 매서드의 이름을 통해 어떤 의존 객채를 설정하는지 알 수 있다, 상황에 따라 혼용하여 사용하면 된다. 


에노테이션 종류
---

@configuration
  - 컨테이너 클레스에 붙인다. 설정클래스라고 한다. 

@Autowired
  -자동 주입되게 하고 싶은 객체에 붙임. 필드(객체 변수)나 새터 매서드에 붙일 수 있음 
  - 자동 의존 주입대상이 Bean에 존재하지 않으면 컴파일 에러  
  - (requried=false) 옵션을 붙이면, 자동 의존 주입 대상이 Bean에 존재하지 않으면 그냥 null을 주입. 이때 만약 기본생성자로 초기화한 값이 있다면 null을 주입하지 않음  
  - 이미 Autowired 를 사용했다면 설정클레스(컨테이너)에서 수동의존 주입과 혼용하지 말고 그냥 자동의존 주입만 사용하는 것이 낫다.

@Bean
  - 컨테이너가 관리할  객체로 등록

@Import(컨테이너.class )
  - 컨테이너 클레스를 임포트할 수 있음. 
  - @Import({컨터이너1.class, 컨테이너2.class}) 와 같이 배열처럼 여러개 임포트 가능 

@Nullable
  - 자동 의존 주입 대상이 bean에서 존재하지 않으면, 그냥 null을 주입.
  - 기본 생성자에서 초기화했더라도 null 주입 

@Qualifier("한정자")
  - @Bean, @Autowired 이노테이션에서 사용. 
  - Autowired로 자동으로 의존 주입 시, 의존 주입할 수 있는 bean이 두개 이상이라 하나로 특정할 수 없게되면 컴파일 에러가 난다. 
  - @Qualifier("한정자") 는 의존 대상 Bean을 특정하여 의존 주입이 일어나도록 한정할 수 있다.  의존주입을 좀 더 세심하게 컨트롤하기위해 사용
  - bean들을 구분하기 위해 붙이는 태그를 한정자라고 한다. 
  - @Qualifier 이노테이션을 사용하지 않을 시, 메서드 이름이 한정자 


AOP(Aspect Oriented Programming) 
---
[출처](https://engkimbs.tistory.com/746) 

어떤 로직을 기준으로 핵심적인 관점, 부가적인 관점으로 나누어서 보고 그 관점을 기준으로 각각 모듈화하겠다는 것이다. 
AOP에서 각 관점을 기준으로 로직을 모듈화한다는 것은 코드들을 부분적으로 나누어서 모듈화하겠다는 의미다. 이때, 소스 코드상에서 다른 부분에 계속 반복해서 쓰는 코드들을 발견할 수 있는 데 이것을 흩어진 관심사 (Crosscutting Concerns)라 부른다. 

- Aspect : 위에서 설명한 흩어진 관심사를 모듈화 한 것. 주로 부가기능을 모듈화함.
- Target : Aspect를 적용하는 곳 (클래스, 메서드 .. )
- Advice : 실질적으로 어떤 일을 해야할 지에 대한 것, 실질적인 부가기능을 담은 구현체
- JointPoint : Advice가 적용될 위치, 끼어들 수 있는 지점. 메서드 진입 지점, 생성자 호출 시점, 필드에서 값을 꺼내올 때 등 다양한 시점에 적용가능
- PointCut : JointPoint의 상세한 스펙을 정의한 것. 'A란 메서드의 진입 시점에 호출할 것'과 같이 더욱 구체적으로 Advice가 실행될 지점을 정할 수 있음



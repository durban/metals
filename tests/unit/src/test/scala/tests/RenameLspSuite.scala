package tests

class RenameLspSuite extends BaseRenameLspSuite("rename") {

  renamed(
    "basic",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val <<toRename>> = 123
       |}
       |/a/src/main/scala/a/Main2.scala
       |package a
       |object Main2{
       |  val toRename = Main.<<toR@@ename>>
       |}
       |""".stripMargin,
    newName = "otherRename"
  )

  renamed(
    "case",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |case class <<User>>(name : String)
       |object Main{
       |  val user = <<User>>.apply("James")
       |  val user2 = <<U@@ser>>(name = "Roger")
       |  user.copy(name = "")
       |}
       |""".stripMargin,
    newName = "Login"
  )

  renamed(
    "generics",
    """/a/src/main/scala/a/Main.scala
      |package a
      |trait S1[X] { def <<torename>>(p: X): String = "" }
      |trait T1[Z] extends S1[Z] { override def <<torename>>(p: Z): String = super.<<torename>>(p) }
      |trait T2[X] extends T1[X] { override def <<torename>>(p: X): String = super.<<torename>>(p) }
      |trait T3[I, J] extends T2[I] { override def <<torename>>(p: I): String = super.<<torename>>(p) }
      |trait T4[I, J] extends T3[J, I] { override def <<torename>>(p: J): String = super.<<torename>>(p) }
      |trait T5[U] extends T4[U, U] { override def <<tore@@name>>(p: U): String = super.<<torename>>(p) }
      |""".stripMargin,
    newName = "newname"
  )

  renamed(
    "match-ret-type",
    """/a/src/main/scala/a/Main.scala
      |package a
      |trait P
      |trait PP extends P
      |trait A { def <<torename>>(a: String): P = ??? }
      |trait B extends A { override def <<tore@@name>>(a: String): PP = ??? }
      |
      |""".stripMargin,
    newName = "newname"
  )

  renamed(
    "across-targets",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val <<toRename>> = 123
       |}
       |/b/src/main/scala/b/Main2.scala
       |package b
       |import a.Main
       |object Main2{
       |  val toRename = Main.<<toR@@ename>>
       |}
       |""".stripMargin,
    newName = "otherRename"
  )

  renamed(
    "unapply",
    """|/a/src/main/scala/a/Main.scala
       |object <<F@@oo>> {
       |  def unapply(s: String): Option[String] = Some("")
       |}
       |
       |object Main{
       |  "foo" match {
       |    case <<Foo>>(s) => ()
       |  }
       |}
       |""".stripMargin,
    newName = "Bar"
  )

  renamed(
    "unapply-param",
    """|/a/src/main/scala/a/Main.scala
       |object Foo {
       |  def unapply(<<nam@@e>>: String): Option[String] = Some(<<name>>)
       |}
       |
       |object Main{
       |  "foo" match {
       |    case Foo(name) => ()
       |  }
       |}
       |""".stripMargin,
    newName = "str"
  )

  renamed(
    "local",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  def hello() = {
       |    val <<toRen@@ame>> = 123
       |    <<toRename>>
       |  }
       |}
       |""".stripMargin,
    newName = "otherRename"
  )

  renamed(
    "method",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  def <<met@@hod>>(abc : String) = true
       |
       |  if(<<method>>("")) println("Is true!")
       |}
       |""".stripMargin,
    newName = "truth"
  )

  renamed(
    "self-type",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |trait <<A@@BC>>
       |trait Alphabet{
       |  this: <<ABC>> =>
       |}
       |object Main{
       |  val a = new Alphabet with <<ABC>>
       |}
       |""".stripMargin,
    newName = "Animal"
  )

  renamed(
    "method-inheritance",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |trait Hello{
       |  def <<method>>(abc : String) : Boolean
       |}
       |
       |class GoodMorning extends Hello {
       |  def <<met@@hod>>(abc : String) = true
       |}
       |""".stripMargin,
    newName = "truth"
  )

  renamed(
    "long-inheritance",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |trait A[T, S] {
       |  def <<method>>(abc : T) : S
       |}
       |
       |abstract class B[T] extends A[T, Boolean] {
       |  def <<method>>(abc : T) : Boolean
       |}
       |
       |abstract class C extends B[String] {
       |  def <<meth@@od>>(abc : String) : Boolean = false
       |}
       |""".stripMargin,
    newName = "truth"
  )

  renamed(
    "multiple-inheritance",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |trait A {
       |  def <<method>>(abc : String) : Boolean
       |}
       |
       |trait B {
       |  def <<method>>(abc : String) : Boolean = true
       |}
       |
       |abstract class C extends B with A {
       |  override def <<meth@@od>>(abc : String) : Boolean = false
       |}
       |""".stripMargin,
    newName = "truth"
  )

  renamed(
    "apply",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object User{
       |  def <<ap@@ply>>(name : String) = name
       |  def apply(name : String, age: Int) = name
       |}
       |object Main{
       |  val toRename = User##.##<<>>("abc")
       |}
       |""".stripMargin,
    newName = "name"
  )

  same(
    "colon-bad",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class User{
       |  def <<:@@:>>(name : String) = name
       |}
       |object Main{
       |  val user = new User()
       |  "" <<::>> user
       |}
       |""".stripMargin
  )

  renamed(
    "colon-good",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class User{
       |  def <<:@@:>>(name : String) = name
       |}
       |object Main{
       |  val user = new User()
       |  "" <<::>> user
       |}
       |""".stripMargin,
    newName = "method:"
  )

  same(
    "unary-bad",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class User{
       |  def <<unary_!>> = false
       |}
       |object Main{
       |  val user = new User()
       |  <<@@!>>user
       |}
       |""".stripMargin
  )

  same(
    "unary-bad2",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class User{
       |  def <<u@@nary_!>> = false
       |}
       |object Main{
       |  val user = new User()
       |  <<!>>user
       |}
       |""".stripMargin
  )

  same(
    "java-classes",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class MyException extends Exce@@ption
       |class NewException extends RuntimeException
       |class NewException2 extends RuntimeException
       |""".stripMargin
  )

  renamed(
    "inheritance",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |abstract class <<An@@imal>>
       |class Dog extends <<Animal>>
       |class Cat extends <<Animal>>
       |""".stripMargin,
    newName = "Tree"
  )

  renamed(
    "companion",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class <<Main>>{}
       |object <<M@@ain>>
       |""".stripMargin,
    newName = "Tree",
    fileRenames =
      Map("a/src/main/scala/a/Main.scala" -> "a/src/main/scala/a/Tree.scala")
  )

  renamed(
    "companion2",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |class <<Ma@@in>>{}
       |object <<Main>>
       |""".stripMargin,
    newName = "Tree",
    fileRenames =
      Map("a/src/main/scala/a/Main.scala" -> "a/src/main/scala/a/Tree.scala")
  )

  renamed(
    "filename-exact-match",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object <<Ma@@in>>
       |object TheMain
       |""".stripMargin,
    newName = "Tree",
    fileRenames =
      Map("a/src/main/scala/a/Main.scala" -> "a/src/main/scala/a/Tree.scala")
  )

  renamed(
    "filename-exact-match-2",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main
       |object <<The@@Main>>
       |""".stripMargin,
    newName = "Tree",
    fileRenames = Map.empty
  )

  renamed(
    "many-files",
    """|/a/src/main/scala/a/A.scala
       |package a
       |object A {
       |  def <<ren@@ameIt>>(a : String) = ""
       |}
       |/a/src/main/scala/a/B.scala
       |package a
       |object B {
       |  val str = A.<<renameIt>>("")
       |}
       |/a/src/main/scala/a/C.scala
       |package a
       |object C {
       |  val str = A.<<renameIt>>("")
       |}
       |/a/src/main/scala/a/D.scala
       |package a
       |object D {
       |  val str = A.<<renameIt>>("")
       |}
       |/a/src/main/scala/a/E.scala
       |package a
       |object E {
       |  val str = A.<<renameIt>>("")
       |}
       |""".stripMargin,
    newName = "iAmRenamed",
    nonOpened = Set(
      "a/src/main/scala/a/C.scala",
      "a/src/main/scala/a/D.scala",
      "a/src/main/scala/a/E.scala"
    )
  )

  renamed(
    "anon",
    """|/a/src/main/scala/a/Main.scala
       |trait Methodable[T] {
       |  def <<method>>(asf: T): Int
       |}
       |
       |trait Alphabet extends Methodable[String] {
       |  def <<method>>(adf: String) = 123
       |}
       |
       |object Main {
       |  val a = new Alphabet {
       |    override def <<me@@thod>>(adf: String): Int = 321
       |  }
       |}
       |""".stripMargin,
    newName = "renamed"
  )

  renamed(
    "java-unchanged",
    """|/a/src/main/java/a/Other.java
       |package a;
       |public class Other{
       |
       |}
       |/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val other = new <<Oth@@er>>()
       |}
       |""".stripMargin,
    newName = "Renamed"
  )

  renamed(
    "compile-error",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val <<toRename>> : Int = 123
       |}
       |/a/src/main/scala/a/Main2.scala
       |package a
       |object Main2{
       |  val toRename = Main.<<toR@@ename>>
       |}
       |""".stripMargin,
    newName = "otherRename",
    breakingChange = (str: String) => str.replaceAll("Int", "String")
  )

  renamed(
    "macro",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |import io.circe.generic.JsonCodec
       |trait LivingBeing
       |@JsonCodec sealed trait <<An@@imal>> extends LivingBeing
       |object <<Animal>> {
       |  case object Dog extends <<Animal>>
       |  case object Cat extends <<Animal>>
       |}
       |""".stripMargin,
    "Tree"
  )

  renamed(
    "macro2",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |import io.circe.generic.JsonCodec
       |trait <<LivingBeing>>
       |@JsonCodec sealed trait Animal extends <<Livi@@ngBeing>>
       |object Animal {
       |  case object Dog extends Animal
       |  case object Cat extends Animal
       |}
       |""".stripMargin,
    "Tree"
  )

  renamed(
    "macro",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |import io.circe.generic.JsonCodec
       |trait LivingBeing
       |@JsonCodec sealed trait <<Animal>> extends LivingBeing
       |object <<Animal>>{
       |  case object Dog extends <<Animal>>
       |  case object Cat extends <<Animal>>
       |}
       |/a/src/main/scala/a/Use.scala
       |package a
       |object Use {
       |  val dog : <<An@@imal>> = <<Animal>>.Dog
       |}
       |""".stripMargin,
    "Tree"
  )

  renamed(
    "implicit-param",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object A {
       |  implicit val <<some@@Name>>: Int = 1
       |  def m[A](implicit a: A): A = a
       |  m[Int]
       |}""".stripMargin,
    newName = "anotherName"
  )

  renamed(
    "nested-symbol",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Foo {
       |  object <<Ma@@in>>
       |}
       |""".stripMargin,
    newName = "Child",
    fileRenames = Map.empty
  )

  renamed(
    "backtick",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val <<greet@@ing>> = "Hello"
       |  "" match {
       |    case `<<greeting>>` =>
       |  }
       |}
       |""".stripMargin,
    newName = "other"
  )

  renamed(
    "backtick2",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  val <<greeting>> = "Hello"
       |  "" match {
       |    case `<<gre@@eting>>` =>
       |  }
       |}
       |""".stripMargin,
    newName = "other"
  )

  renamed(
    "backtick3",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  def local = {
       |    val <<greet@@ing>> = "Hello"
       |    "" match {
       |      case `<<greeting>>` =>
       |    }
       |  }
       |}
       |""".stripMargin,
    newName = "other"
  )

  // If renaming in VS Code, backticks are taken as part of the name
  renamed(
    "backtick4",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |object Main{
       |  def local = {
       |    val greeting = "Hello"
       |    "" match {
       |      case `gre@@eting` =>
       |    }
       |  }
       |}
       |""".stripMargin,
    newName = "`greeting`"
  )

  renamed(
    "params",
    """|/a/src/main/scala/a/Main.scala
       |case class Name(<<va@@lue>>: String)
       |
       |object Main {
       |  val name1 = Name(<<value>> = "42")
       |   .copy(<<value>> = "43")
       |   .copy(<<value>> = "43")
       |   .<<value>>
       |  val name2 = Name(<<value>> = "44")
       |}
       |""".stripMargin,
    newName = "name"
  )

  // tests currently not working correctly due to issues in SemanticDB
  // https://github.com/scalameta/metals/issues/1086 - most likely due to scalameta bug
  renamed(
    "constructor",
    """|/a/src/main/scala/a/Main.scala
       |case class Name(<<va@@lue>>: String)
       |
       |object Main {
       |  val name2 = new Name(value = "44")
       |}
       |""".stripMargin,
    newName = "name"
  )

  // https://github.com/scalameta/scalameta/issues/1909
  renamed(
    "type-params",
    """|/a/src/main/scala/a/Main.scala
       |package a
       |trait <<ABC>>
       |class CBD[T <: <<AB@@C>>]
       |object Main{
       |  val a = classOf[ABC]
       |  val b = new CBD[<<ABC>>]
       |}
       |""".stripMargin,
    newName = "Animal"
  )

}
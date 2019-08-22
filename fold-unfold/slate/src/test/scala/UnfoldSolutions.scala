object UnfoldSolutions {
  def unfold[A, B](initial: A)(fun: A => Option[(A, B)]): Stream[B] =
    fun(initial).map { case (a, b) => b #:: unfold(a)(fun) }.getOrElse(Stream.empty)
  def unfold0[A](a: A)(fun: A => Option[A]): Stream[A] =
    unfold(a)({state => fun(state).map({newState => (newState, newState)})})

  def unfoldE[A, B](initial: A)(fun: A => Stream[Either[A, B]]): Stream[B] =
    fun(initial).flatMap {
      case Left(a) => unfoldE(a)(fun)
      case Right(b) => Stream(b)
    }

}
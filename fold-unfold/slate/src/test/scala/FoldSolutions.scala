object FoldSolutions {
  def runList[I, O](fold: Fold[I, O], data: List[I]) = {
    fold.summarize(
      data.map(i => fold.tally(i))
        .foldRight(fold.monoid.empty)((x, y) => fold.monoid.combine(x, y)) // or combineAll
    )
  }
}
package tn.keyrus.pfe.imdznd.transactionservice.dirtyworld.model.dto

import java.util.Optional

data class DateRangeDTO(
    val startDate: DateDTO,
    val endDate: DateDTO,
) {

    fun checkStartDateAndEndDate() =
        when {
            this.startDate.toLocalDate().isLeft or this.endDate.toLocalDate().isLeft ->
                Optional.of(DateRangeError.DateIsNotValidError)
            this.startDate.toLocalDate().get()!!.isAfter(this.endDate.toLocalDate().get()!!) ->
                Optional.of(DateRangeError.EndDateBeforeStartDateError)
            else -> Optional.empty()
        }

    sealed interface DateRangeError {
        object DateIsNotValidError : DateRangeError
        object EndDateBeforeStartDateError : DateRangeError
    }

}
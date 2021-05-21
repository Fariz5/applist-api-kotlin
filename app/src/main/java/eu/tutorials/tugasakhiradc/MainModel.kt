package eu.tutorials.tugasakhiradc

//"id": 1,
//"title": "accusamus beatae ad facilis cum similique qui sunt",
//"url": "https://via.placeholder.com/600/92c952",

data class MainModel (val result: ArrayList<Result>){
    data class Result (
        val id: Int,
        val title: String,
        val image: String
            )
}
package jorgecasariego.testwithkotlin.io

import jorgecasariego.testwithkotlin.users.UserEntity
import org.amshove.kluent.shouldEqual
import org.junit.Test

/**
 * Created by jorgecasariego on 2/8/17.
 */
class SerializerTest {
    private val JSON_RESPONSE = "{\n \"id\": 1,\n " +
            "\"cover_url\": \"http://www.android10.org/myapi/cover_1.jpg\",\n " +
            "\"full_name\": \"Simon Hill\",\n " +
            "\"description\": \"Curabitur gravida nisi at nibh. In hac habitasse " +
            "platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer " +
            "eget, rutrum at, lorem.\\n\\nInteger tincidunt ante vel ipsum. " +
            "Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo " +
            "placerat.\\n\\nPraesent blandit. Nam nulla. Integer pede justo, " +
            "lacinia eget, tincidunt eget, tempus vel, pede.\",\n " +
            "\"followers\": 7484,\n " +
            "\"email\": \"jcooper@babbleset.edu\"\n}"

    private var serializer = Serializer()

    @Test
    fun shouldSerialize() {
        val userEntityOne = serializer.deserialize(JSON_RESPONSE, UserEntity::class.java)
        val jsonString = serializer.serialize(userEntityOne, UserEntity::class.java)
        val userEntityTwo = serializer.deserialize(jsonString, UserEntity::class.java)

        userEntityOne.userId shouldEqual userEntityTwo.userId
        userEntityOne.fullname shouldEqual userEntityTwo.fullname
        userEntityOne.followers shouldEqual userEntityTwo.followers
    }

    @Test
    fun shouldDeserialize() {
        val userEntity = serializer.deserialize(JSON_RESPONSE, UserEntity::class.java)

        userEntity.userId shouldEqual 1
        userEntity.fullname shouldEqual "Simon Hill"
        userEntity.followers shouldEqual 7484
    }
}
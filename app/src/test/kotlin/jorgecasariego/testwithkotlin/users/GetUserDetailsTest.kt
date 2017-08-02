package jorgecasariego.testwithkotlin.users

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import jorgecasariego.testwithkotlin.executor.PostExecutionThread
import jorgecasariego.testwithkotlin.executor.ThreadExecutor
import jorgecasariego.testwithkotlin.users.GetUserDetails
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

/**
 * Created by jorgecasariego on 2/8/17.
 */
class GetUserDetailsTest {
    private val USER_ID = 123

    private lateinit var getUserDetails: GetUserDetails

    private val userRepository: UserRepository = mock()
    private val threadExecutor: ThreadExecutor = mock()
    private val postExecutionThread: PostExecutionThread = mock()

    @Before
    fun setUp() {
        getUserDetails = GetUserDetails(userRepository, threadExecutor, postExecutionThread)
    }

    @Test
    fun shouldGetUserDetails() {
        getUserDetails.buildUseCaseObservable(GetUserDetails.Params.forUser(USER_ID));

        verify(userRepository).user(USER_ID)
        verifyNoMoreInteractions(userRepository)
        Mockito.verifyZeroInteractions(postExecutionThread)
        Mockito.verifyZeroInteractions(threadExecutor)
    }

}
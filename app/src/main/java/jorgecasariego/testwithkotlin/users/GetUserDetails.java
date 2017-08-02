package jorgecasariego.testwithkotlin.users;

import io.reactivex.Observable;
import jorgecasariego.testwithkotlin.executor.PostExecutionThread;
import jorgecasariego.testwithkotlin.executor.ThreadExecutor;

public class GetUserDetails {

  private final UserRepository userRepository;
  private final ThreadExecutor threadExecutor;
  private final PostExecutionThread postExecutionThread;

  GetUserDetails(UserRepository userRepository, ThreadExecutor threadExecutor,
      PostExecutionThread postExecutionThread) {
    this.userRepository = userRepository;
    this.threadExecutor = threadExecutor;
    this.postExecutionThread = postExecutionThread;
  }

  Observable<User> buildUseCaseObservable(Params params) {
    return this.userRepository.user(params.userId);
  }

  static final class Params {

    private final int userId;

    private Params(int userId) {
      this.userId = userId;
    }

    public static Params forUser(int userId) {
      return new Params(userId);
    }
  }

}

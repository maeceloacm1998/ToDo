import androidx.test.platform.app.InstrumentationRegistry
import com.example.todo.di.HomeDependencyInjectModules
import com.example.todo.di.RoomDependencyInjectModules
import com.example.todo.utils.KoinUtils
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class KoinTestRule() : TestWatcher() {
    override fun starting(description: Description) {
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext.applicationContext)
            KoinUtils.addModules(*HomeDependencyInjectModules.modules)
            KoinUtils.addModules(*RoomDependencyInjectModules.modules)
        }
    }
    override fun finished(description: Description) {
        stopKoin()
    }
}
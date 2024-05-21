package lazy.initialization.examples.exampleScreen

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import lazy.initialization.examples.databinding.ActivityExampleScreenBinding
import lazy.initialization.examples.exampleScreen.di.LazyGraphComponent
import lazy.initialization.examples.exampleScreen.di.SyncComponent
import lazy.initialization.examples.exampleScreen.di.buildLazyGraphComponent
import lazy.initialization.examples.exampleScreen.di.buildSyncComponent
import lazy.initialization.examples.exampleScreen.di.LazyVmComponent
import lazy.initialization.examples.exampleScreen.di.buildLazyVmComponent
import lazy.initialization.examples.measure
import javax.inject.Inject

/**
 * @author a.taganov
 */
internal class ExampleScreen : AppCompatActivity() {

    private val viewBinding: ActivityExampleScreenBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityExampleScreenBinding.inflate(layoutInflater)
    }

    @Inject
    lateinit var viewModel: ExampleViewModel

    private val syncComponent: SyncComponent by lazy(LazyThreadSafetyMode.NONE) {
        buildSyncComponent(application)
    }

    private val lazyVmComponent: LazyVmComponent by lazy(LazyThreadSafetyMode.NONE) {
        buildLazyVmComponent(application)
    }

    private val lazyGraphComponent: LazyGraphComponent by lazy(LazyThreadSafetyMode.NONE) {
        buildLazyGraphComponent(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnUsecaseA.setOnClickListener { usecaseA() }
        viewBinding.btnUsecaseB.setOnClickListener { usecaseB() }
        viewBinding.btnUsecaseC.setOnClickListener { usecaseC() }
        val strategy: InjectStrategy = intent.getParcelableExtra(KEY_STRATEGY) ?: InjectStrategy.SYNC
        measure("Inject time with $strategy") {
            inject(strategy)
        }
    }

    private fun usecaseA() { viewModel.callA() }

    private fun usecaseB() { viewModel.callB() }

    private fun usecaseC() { viewModel.callC() }

    private fun inject(strategy: InjectStrategy) {
        when (strategy) {
            InjectStrategy.SYNC -> syncComponent.inject(this)
            InjectStrategy.LAZY_VM -> lazyVmComponent.inject(this)
            InjectStrategy.LAZY_GRAPH -> lazyGraphComponent.inject(this)
        }
    }

    companion object {
        private const val KEY_STRATEGY: String = "Strategy"
        fun createIntent(context: Context, strategy: InjectStrategy): Intent {
            val intent = Intent(context, ExampleScreen::class.java)
            intent.putExtra(KEY_STRATEGY, strategy as Parcelable)
            return intent
        }
    }
}

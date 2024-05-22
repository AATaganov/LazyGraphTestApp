package lazy.initialization.examples

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import lazy.initialization.examples.exampleScreen.InjectStrategy
import lazy.initialization.examples.exampleScreen.InjectStrategy.LAZY_COMPONENTS
import lazy.initialization.examples.exampleScreen.InjectStrategy.LAZY_GRAPH
import lazy.initialization.examples.exampleScreen.InjectStrategy.LAZY_VM
import lazy.initialization.examples.exampleScreen.InjectStrategy.SYNC
import lazy.initialization.examples.databinding.ActivityMainBinding
import lazy.initialization.examples.exampleScreen.ExampleScreen

class MainActivity : AppCompatActivity() {

    private val viewBinding: ActivityMainBinding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        viewBinding.btnSync.setOnClickListener { openExampleScreen(SYNC) }
        viewBinding.btnLazyVm.setOnClickListener { openExampleScreen(LAZY_VM) }
        viewBinding.btnLazyGraph.setOnClickListener { openExampleScreen(LAZY_GRAPH) }
        viewBinding.btnLazyComponents.setOnClickListener { openExampleScreen(LAZY_COMPONENTS) }
    }

    private fun openExampleScreen(strategy: InjectStrategy) {
        startActivity(ExampleScreen.createIntent(this, strategy))
    }
}

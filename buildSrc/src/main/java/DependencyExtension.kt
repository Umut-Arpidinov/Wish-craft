import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.addUiDependencies() {
    uiDependencies.forEach {
        add("implementation", it)
    }
}


fun DependencyHandler.addKoinDependencies() {
    koinDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addNetworkDependencies() {
    networkDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addCoroutinesDependencies() {
    coroutinesDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addAndroidLifecycleDependencies() {
    add("implementation", Dependencies.runtimKtx)
    add("implementation", Dependencies.viewModel)
    add("implementation", Dependencies.viewModelSavedSate)
    add("implementation", Dependencies.liveData)
    add("kapt", Dependencies.lifeCycleCompiler)
}


fun DependencyHandler.addNavigationDependencies() {
    navigationDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addSafeArgs() {
    add("classpath", Dependencies.safeArgs)
}

fun DependencyHandler.addAndroidTestsDependencies() {
    add("testImplementation", Dependencies.jUnit)
    add("androidTestImplementation", Dependencies.jUnitExt)
    add("androidTestImplementation", Dependencies.espresso)
}

fun DependencyHandler.addDotsIndicatorDependency() {
    add("implementation", Dependencies.dotsIndicator)
}

fun DependencyHandler.addTimberDependency() {
    add("implementation", Dependencies.timber)
}

fun DependencyHandler.addCameraDependencies() {
    cameraxDependencies.forEach {
        add("implementation", it)
    }
}

fun DependencyHandler.addExifInterface() {
    add("implementation", Dependencies.exifInterface)
}

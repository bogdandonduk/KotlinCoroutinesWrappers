package bogdandonduk.kotlincoroutineswrapperslib

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

object KotlinCoroutinesScopes {
    private val ScopeIO = CoroutineScope(IO)
    private val ScopeIOJobs = mutableMapOf<String, Job>()

    fun launchIOScopeJob(jobKeyToSave: String, replaceJobWithSameKeyIfRunning: Boolean = false, job: (CoroutineScope) -> Unit) {
        if(replaceJobWithSameKeyIfRunning) {
            ScopeIOJobs[jobKeyToSave]?.cancel()
            ScopeIOJobs.remove(jobKeyToSave)
        }

        if(!ScopeIOJobs.containsKey(jobKeyToSave))
            ScopeIOJobs[jobKeyToSave] = ScopeIO.launch {
                job.invoke(this)
            }
    }

    fun getIOScopeJob(key: String) = ScopeIOJobs[key]

    fun cancelIOScopeJob(key: String) {
        ScopeIOJobs[key]?.cancel()
    }

    fun getAllIOScopeJobs() = ScopeIOJobs

    fun cancelAllIOScopeJob() {
        ScopeIOJobs.forEach {
            it.value.cancel()
        }

        ScopeIOJobs.clear()
    }

    private val ScopeDefault = CoroutineScope(Default)
    private val ScopeDefaultJobs = mutableMapOf<String, Job>()

    fun launchDefaultScopeJob(jobKeyToSave: String, replaceJobWithSameKeyIfRunning: Boolean = false, job: (CoroutineScope) -> Unit) {
        if(replaceJobWithSameKeyIfRunning) {
            ScopeDefaultJobs[jobKeyToSave]?.cancel()
            ScopeDefaultJobs.remove(jobKeyToSave)
        }

        if(!ScopeDefaultJobs.containsKey(jobKeyToSave))
            ScopeDefaultJobs[jobKeyToSave] = ScopeDefault.launch {
                job.invoke(this)
            }
    }

    fun getDefaultScopeJob(key: String) = ScopeDefaultJobs[key]

    fun cancelDefaultScopeJob(key: String) {
        ScopeDefaultJobs[key]?.cancel()
    }

    fun getAllDefaultScopeJobs() = ScopeDefaultJobs

    fun cancelAllDefaultScopeJob() {
        ScopeDefaultJobs.forEach {
            it.value.cancel()
        }

        ScopeDefaultJobs.clear()
    }

    private val ScopeMain = CoroutineScope(Main)
    private val ScopeMainJobs = mutableMapOf<String, Job>()

    fun launchMainScopeJob(jobKeyToSave: String, replaceJobWithSameKeyIfRunning: Boolean = false, job: (CoroutineScope) -> Unit) {
        if(replaceJobWithSameKeyIfRunning) {
            ScopeMainJobs[jobKeyToSave]?.cancel()
            ScopeMainJobs.remove(jobKeyToSave)
        }

        if(!ScopeMainJobs.containsKey(jobKeyToSave))
            ScopeMainJobs[jobKeyToSave] = ScopeMain.launch {
                job.invoke(this)
            }
    }

    fun getMainScopeJob(key: String) = ScopeMainJobs[key]

    fun cancelMainScopeJob(key: String) {
        ScopeMainJobs[key]?.cancel()
    }

    fun getAllMainScopeJobs() = ScopeMainJobs

    fun cancelAllMainScopeJob() {
        ScopeMainJobs.forEach {
            it.value.cancel()
        }

        ScopeMainJobs.clear()
    }

    private val ScopeMainImmediate = CoroutineScope(Main.immediate)
    private val ScopeMainImmediateJobs = mutableMapOf<String, Job>()

    fun launchMainImmediateScopeJob(jobKeyToSave: String, replaceJobWithSameKeyIfRunning: Boolean = false, job: (CoroutineScope) -> Unit) {
        if(replaceJobWithSameKeyIfRunning) {
            ScopeMainImmediateJobs[jobKeyToSave]?.cancel()
            ScopeMainImmediateJobs.remove(jobKeyToSave)
        }

        if(!ScopeMainImmediateJobs.containsKey(jobKeyToSave))
            ScopeMainImmediateJobs[jobKeyToSave] = ScopeMainImmediate.launch {
                job.invoke(this)
            }
    }

    fun getMainImmediateScopeJob(key: String) = ScopeMainJobs[key]

    fun cancelMainImmediateScopeJob(key: String) {
        ScopeMainImmediateJobs[key]?.cancel()
    }

    fun getAllMainImmediateScopeJobs() = ScopeMainImmediateJobs

    fun cancelAllMainImmediateScopeJob() {
        ScopeMainImmediateJobs.forEach {
            it.value.cancel()
        }

        ScopeMainImmediateJobs.clear()
    }
}
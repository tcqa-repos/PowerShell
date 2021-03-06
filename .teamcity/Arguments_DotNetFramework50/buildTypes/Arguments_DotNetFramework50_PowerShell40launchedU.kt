package Arguments_DotNetFramework50.buildTypes

import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.powerShell
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.BuildFailureOnText
import jetbrains.buildServer.configs.kotlin.v2019_2.failureConditions.failOnText

object Arguments_DotNetFramework50_PowerShell40launchedU : BuildType({
    name = "PowerShell 4.0 launched under .Net 5.0"
    paused = true

    vcs {
        root(_Self.vcsRoots.HttpsGithubComJuliaReshMyProjectGit)

        checkoutMode = CheckoutMode.ON_SERVER
    }

    steps {
        powerShell {
            name = "One argument with space"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """"one test-message"""")
        }
        powerShell {
            name = "Two arguments separated by space"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", "two test-messages")
        }
        powerShell {
            name = "Three arguments separated by new line"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                three 
                test
                messages
            """.trimIndent())
        }
        powerShell {
            name = "Argument with dot"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """"arg with.dot" 8.1""")
        }
        powerShell {
            name = "With special symbols"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                "^pattern1${'$'}"
                ^pattern2${'$'}
            """.trimIndent())
        }
        powerShell {
            name = "With quotes"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                "some line with ^"quotes^" inside"
                "anotherline with "quotes" inside"
            """.trimIndent())
        }
        powerShell {
            name = "One slash at the end"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                slash\
                "a line with one slash \"
                "another line with one slash \"
            """.trimIndent())
        }
        powerShell {
            name = "Two slashes at the end"
            minRequiredVersion = "4.0"
            scriptMode = file {
                path = "argument.ps1"
            }
            noProfile = false
            param("jetbrains_powershell_scriptArguments", """
                slashes\\
                "a line with two slashes \\"
                "a line with two escaped slashes ^\^\"
            """.trimIndent())
        }
        powerShell {
            name = "Two slashes at the end (1)"
            minRequiredVersion = "4.0"
            scriptMode = script {
                content = """
                    "First argument: ${'$'}(${'$'}args[0])"
                    "Second argument: ${'$'}(${'$'}args[1])"
                    "Third argument: ${'$'}(${'$'}args[2])"
                """.trimIndent()
            }
            noProfile = false
            param("jetbrains_powershell_script_file", "argument.ps1")
            param("jetbrains_powershell_scriptArguments", """
                slash\
                "a line with one escaped slash ^\"
                "a line with one slash \"
            """.trimIndent())
        }
    }

    failureConditions {
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^one test-message${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^two${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^test-messages${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^three${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^test${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = "^messages${'$'}"
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = """^arg with\.dot${'$'}"""
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = """^\^pattern1\${'$'}${'$'}"""
            reverse = true
        }
        failOnText {
            conditionType = BuildFailureOnText.ConditionType.REGEXP
            pattern = """^\^pattern2\${'$'}${'$'}"""
            reverse = true
        }
    }

    requirements {
        equals("powershell_x86", "5.0")
    }
})

package com.soft.nice.learncompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.soft.nice.learncompose.ui.theme.LearnComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnComposeTheme {
               MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    // TODO: This state should be hoisted 这个状态应该被提升
    // shouldShowOnboarding 应该在登机时提示
    var shouldShowOnboaring by remember {  mutableStateOf(true) }
    if(shouldShowOnboaring) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboaring = false})
    }else {
        Greetings()
    }
}

@Composable
fun Greetings(names: List<String> = listOf("World", "Compose", "Andy", "Jack", "Leo")) {
    // A surface container using the 'background' color from the theme
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(modifier = Modifier.padding(vertical = 4.dp)) {
            for(name in names) {
                Greeting(name = name)
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    //声明一个展开变数
    //在重组之后保留状态，用remember记忆
    //mutableStateOf:表示当这个值更改时，需要重组它的依赖项，因此现在返回一个状态实例，而不是单纯一个布尔值
    val expanded = remember { mutableStateOf(false) }

    //extraPadding额外的填充
    val extraPadding = if (expanded.value) 48.dp else 0.dp

    //使用surface把文字包裹起来
    Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.padding(8.dp, vertical = 4.dp)) {
        Row(modifier =  Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)) {
                Text(text = "Hello $name!")
                Text(text = name)
            }

            //添加按钮
            OutlinedButton(onClick = { expanded.value = !expanded.value },
                border = BorderStroke(1.0.dp, Color.White)

            ) {
                Text(if(expanded.value) "Show less" else "Show more", color = Color.Yellow)
            }
        }

    }

}


@Composable
//登机画面
fun OnboardingScreen(
    onContinueClicked: () -> Unit
) {
    // TODO: This state should be hoisted 这个状态应该被提升
    // shouldShowOnboarding 应该在登机时提示
    var shouldShowOnboaring by remember {  mutableStateOf(true) }

    Surface {
        Column (
            //尽可能填充空间
            modifier = Modifier.fillMaxSize(),
            //垂直排列
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("欢迎来到基础代码实验室！")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    LearnComposeTheme {
        OnboardingScreen(onContinueClicked = {})
    }
}




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LearnComposeTheme {
        MyApp()
    }
}
Dim $dialogTitle="文件下载"
WinActivate($dialogTitle)
WinWaitActive($dialogTitle)
Sleep(1000)
ControlClick($dialogTitle,"","Button2")
Sleep(1000)
WinActivate("另存为")
WinWaitActive("另存为")
Sleep(1000)
ControlSetText($title,"","Edit1",$CmdLine[1])
ControlClick("另存为","","Button1")


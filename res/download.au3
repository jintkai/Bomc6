Dim $dialogTitle="�ļ�����"
WinActivate($dialogTitle)
WinWaitActive($dialogTitle)
Sleep(1000)
ControlClick($dialogTitle,"","Button2")
Sleep(1000)
WinActivate("���Ϊ")
WinWaitActive("���Ϊ")
Sleep(1000)
ControlSetText($title,"","Edit1",$CmdLine[1])
ControlClick("���Ϊ","","Button1")


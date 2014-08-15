Dim $acount="jinkai";
Dim $passwd="jinkai1987"
dim $title="Windows °²È«"
WinActivate($title)
WinWaitActive($title)
Sleep(1000)
ControlSetText($title,"","Edit1",$acount)
Sleep(1000)
ControlSetText($title,"","Edit2",$passwd)
ControlClick($title,"","Button2")
Sleep(1000)
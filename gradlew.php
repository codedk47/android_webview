<?php
#ENV set JAVA_HOME = C:\Program Files\Android\Android Studio\jre
#ENV set JAVA_HOME = C:\Program Files\Android\Android Studio\jbr
#group = 'org.chromium.android_webview'
$apks = [];
for ($i = 0; $i < 20; $i++)
{
	$build = file_get_contents('build.gradle');
	$random = 'aw' . bin2hex(random_bytes(7));
	$build = preg_replace('/group\s*=\s*\'[^\']+/', "group = 'org.chromium.{$random}", $build);
	if (file_put_contents('build.gradle', $build) ===  strlen($build))
	{
		exec('gradlew --no-build-cache assembleDebug', $output, $result_code);
		if ($result_code === 0 && rename('build/outputs/apk/debug/android_webview-debug.apk', "build/outputs/apk/debug/{$random}.apk"))
		{
			$apks[] = "{$random}.apk";
		}
	}
}
is_file('build/outputs/apk/debug/apks.zip') && unlink('build/outputs/apk/debug/apks.zip');
$zip = new ZipArchive;
if ($zip->open('build/outputs/apk/debug/apks.zip', ZIPARCHIVE::CREATE))
{
	$delapks = [];
	foreach ($apks as $apk)
	{
		if ($zip->addFile("build/outputs/apk/debug/{$apk}", $apk))
		{
			$delapks[] = $apk;
		}
	}
	if ($zip->close())
	{
		foreach ($delapks as $apk)
		{
			unlink("build/outputs/apk/debug/{$apk}");
		}
	}
}
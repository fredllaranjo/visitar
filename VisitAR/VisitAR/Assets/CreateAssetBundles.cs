using System.Collections;
using UnityEngine;
#if UNITY_EDITOR
using UnityEditor;
#endif
using System.IO;

public class CreateAssetBundles
{
    private static string assetBundleDirectory = "Assets/AssetBundles";
#if UNITY_EDITOR
    [MenuItem("Assets/Build AssetBundles")]
    static void BuildAllAssetBundles()
    {
        if (!Directory.Exists(assetBundleDirectory))
        {
            Directory.CreateDirectory(assetBundleDirectory);
        }
        BuildPipeline.BuildAssetBundles(assetBundleDirectory, BuildAssetBundleOptions.None, BuildTarget.Android);
    }
    // [MenuItem("Assets/Upload AssetBundles")]
    // static void UploadAllAssetBundles()
    // {
    //     if (!Directory.Exists(assetBundleDirectory))
    //     {
    //         string[] files = Directory.GetFiles(assetBundleDirectory);

    //     }
    // }
    // public static IEnumerator UploadFile(String filename)
    // {
    //     //TODO Fred> It could be changed to call upload directly from Unity
    //     List<IMultipartFormSection> form = new List<IMultipartFormSection>();
    //     form.Add(new MultipartFormDataSection("file", username));
    //     form.Add(new MultipartFormDataSection("Password", password));

    //     UnityWebRequest request = UnityWebRequest.Post(InterfaceTools.GetAccountsUrl("authenticate"), form);
    //     request.SetRequestHeader("Accept", "application/json");
    //     yield return request.Send();
    // }
#endif
}

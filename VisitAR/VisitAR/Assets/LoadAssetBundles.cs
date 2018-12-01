using System;
using System.Collections;
using UnityEngine;
using UnityEngine.Networking;
public class LoadAssetBundles : MonoBehaviour
{

    //public string assetBundleEndpoint = "http://localhost:8080/visitar/api/assetbundles";
    public string assetBundleEndpoint;
    public string assetBundleName;
    public Transform targetZamHolder;
    public Transform targetKapowHolder;
    public Transform targetBoomHolder;
    public GameObject targetZamRef;
    public GameObject targetKapowRef;
    public GameObject targetBoomRef;
    public GameObject elseNotFound;
    // Use this for initialization
    void Start()
    {
        if (assetBundleEndpoint == "")
        {
            //assetBundleEndpoint = "file:///" + Application.dataPath + "/AssetBundles";
            assetBundleEndpoint = "http://192.168.43.57:8080/visitar/api/assetbundles";
        }
        if (assetBundleName == "")
        {
            assetBundleName = "onomatoprefab";
        }
        string uri = assetBundleEndpoint + "/" + assetBundleName;
        //A
        //StartCoroutine(GetAssetBundle(uri));
        //B
        //WWW www = new WWW(uri);
        //StartCoroutine(WaitForReq(www));
        //C
        //StartCoroutine(InstantiateObject());
        //D
        //StartCoroutine(GetAssetBundleSync(uri));
        //E
        StartCoroutine(GetAssetBundleUWR(uri));
    }
    IEnumerator GetAssetBundleUWR(string uri)
    {
        using (UnityWebRequest uwr = UnityWebRequest.GetAssetBundle(uri))
        {
            yield return uwr.SendWebRequest();

            if (uwr.isNetworkError || uwr.isHttpError)
            {
                Debug.Log(uwr.error);
            }
            else
            {
                // Get downloaded asset bundle
                AssetBundle bundle = DownloadHandlerAssetBundle.GetContent(uwr);
                LoadFromBundle(bundle);
            }
        }
    }
    IEnumerator GetAssetBundleSync(string uri)
    {
        UnityWebRequest www = UnityWebRequest.GetAssetBundle(uri);
        yield return www.SendWebRequest();

        if (www.error != null)
        {
            Debug.Log(www.error);
            www.Dispose();
            www = null;
            yield break;
        }
        else
        {
            AssetBundle bundle = ((DownloadHandlerAssetBundle)www.downloadHandler).assetBundle;
            LoadFromBundle(bundle);
            www.Dispose();
            www = null;
        }
    }
    IEnumerator GetAssetBundle(string uri)
    {
        UnityWebRequest www = UnityWebRequest.GetAssetBundle(uri);
        yield return www.SendWebRequest();

        if (www.isNetworkError || www.isHttpError)
        {
            Debug.Log(www.error);
        }
        else
        {
            AssetBundle bundle = DownloadHandlerAssetBundle.GetContent(www);
            GameObject zam = bundle.LoadAsset<GameObject>("zam");
            GameObject kapow = bundle.LoadAsset<GameObject>("kapow");
            GameObject boom = bundle.LoadAsset<GameObject>("boom");
            Debug.Log(zam);
            Instantiate(zam, targetZamHolder);
            Debug.Log(kapow);
            Instantiate(kapow, targetKapowHolder);
            Debug.Log(boom);
            Instantiate(boom, targetBoomHolder);
        }
    }
    IEnumerator WaitForReq(WWW www)
    {
        yield return www;
        AssetBundle bundle = www.assetBundle;
        if (www.error == null)
        {
            AssetBundleRequest zam = bundle.LoadAssetAsync<GameObject>("zam");
            AssetBundleRequest kapow = bundle.LoadAssetAsync<GameObject>("kapow");
            AssetBundleRequest boom = bundle.LoadAssetAsync<GameObject>("boom");
            yield return zam;
            Debug.Log(zam.asset);
            Instantiate(zam.asset as GameObject, targetZamHolder);
            // yield return kapow;
            // Debug.Log(kapow.asset);
            // Instantiate(kapow.asset as GameObject, targetKapowHolder);
            // yield return boom;
            // Debug.Log(boom.asset);
            // Instantiate(boom.asset as GameObject, targetBoomHolder);
        }
        else
        {
            Debug.Log(www.error);
        }
    }
    IEnumerator InstantiateObject()
    {
        //string uri = "file:///" + Application.dataPath + "/AssetBundles/" + assetBundleName;
        string uri = assetBundleEndpoint + "/" + assetBundleName;
        UnityEngine.Networking.UnityWebRequest request = UnityEngine.Networking.UnityWebRequest.GetAssetBundle(uri, 0);
        yield return request.SendWebRequest();
        AssetBundle bundle = DownloadHandlerAssetBundle.GetContent(request);
        GameObject zam = bundle.LoadAsset<GameObject>("Onomato_Zam");
        GameObject kapow = bundle.LoadAsset<GameObject>("Onomato_Kapow");
        GameObject boom = bundle.LoadAsset<GameObject>("Onomato_Boom");
        Instantiate(zam);
        Instantiate(kapow);
        Instantiate(boom);
    }
    private void LoadFromBundle(AssetBundle bundle)
    {
        GameObject zam = bundle.LoadAsset<GameObject>("zam");
        GameObject kapow = bundle.LoadAsset<GameObject>("kapow");
        GameObject boom = bundle.LoadAsset<GameObject>("boom");
        if (zam != null)
        {
            Debug.Log(zam);
            GameObject inSceneZam = Instantiate(zam);
            inSceneZam.transform.parent = targetZamHolder;
            //inSceneZam.transform.localScale = zam.transform.localScale * 2;
            //inSceneZam.transform.eulerAngles = new Vector3(-90f, 180f, 0f);
            //inSceneZam.transform.eulerAngles.Set(-90f, 180f, 0f);
            //inSceneZam.transform.position = new Vector3(0.5f, 0.5f, 0.1f);
            inSceneZam.transform.localRotation = Quaternion.Euler(-90, 180, 0);
            inSceneZam.transform.localPosition = Vector3.zero;
            inSceneZam.transform.localScale = Vector3.one;
            targetZamRef.SetActive(false);
        }
        else
        {
            GameObject inSceneZam = Instantiate(elseNotFound);
            inSceneZam.transform.parent = targetZamHolder;
            inSceneZam.transform.localScale = elseNotFound.transform.localScale;
            inSceneZam.transform.rotation = elseNotFound.transform.rotation;
            inSceneZam.transform.position = new Vector3(0, 0, 1);
            targetZamRef.SetActive(false);
        }
        if (kapow != null)
        {
            Debug.Log(kapow);
            GameObject inSceneKapow = Instantiate(kapow);
            inSceneKapow.transform.parent = targetKapowHolder;
            inSceneKapow.transform.localScale = kapow.transform.localScale;
            inSceneKapow.transform.rotation = kapow.transform.rotation;
            inSceneKapow.transform.position = new Vector3(0, 0, 1);
            targetKapowRef.SetActive(false);
        }
        else
        {
            GameObject inSceneKapow = Instantiate(elseNotFound);
            inSceneKapow.transform.parent = targetKapowHolder;
            inSceneKapow.transform.localScale = elseNotFound.transform.localScale;
            inSceneKapow.transform.rotation = elseNotFound.transform.rotation;
            inSceneKapow.transform.position = new Vector3(0, 0, 1);
            targetKapowRef.SetActive(false);
        }
        if (boom != null)
        {
            Debug.Log(boom);
            GameObject inSceneBoom = Instantiate(boom);
            inSceneBoom.transform.parent = targetBoomHolder;
            inSceneBoom.transform.localScale = boom.transform.localScale;
            inSceneBoom.transform.rotation = boom.transform.rotation;
            inSceneBoom.transform.position = new Vector3(0, 0, 1);
            targetBoomRef.SetActive(false);
        }
        else
        {
            GameObject inSceneBoom = Instantiate(elseNotFound);
            inSceneBoom.transform.parent = targetBoomHolder;
            inSceneBoom.transform.localScale = elseNotFound.transform.localScale;
            inSceneBoom.transform.rotation = elseNotFound.transform.rotation;
            inSceneBoom.transform.position = new Vector3(0, 0, 1);
            targetBoomRef.SetActive(false);
        }

        // try to cleanup memory
        Resources.UnloadUnusedAssets();
        bundle.Unload(false);
        bundle = null;
    }
}

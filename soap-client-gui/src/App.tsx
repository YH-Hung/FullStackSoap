import './App.css'
import {ProductDataUpload} from "./component/ProductDataUpload.tsx";
import {BffResponse} from "./type/BffInterface.ts";
import {useState} from "react";
import {LoadingIndicator} from "./component/LoadingIndicator.tsx";
import {ResultTable} from "./component/ResultTable.tsx";
import {uploadFile} from "./service/bffUtil.ts";
import {message} from "antd";

function App() {
    const [loading, setLoading] = useState(false);
    const [results, setResults] = useState<BffResponse>([]);

    const handleFileUpload = async (file: File) => {
        setLoading(true);
        setResults([]);

        try {
            const data = await uploadFile(file);
            setResults(data);
            message.success('File processed successfully!');
        } catch (error: any) {
            if (error.message.includes('timeout')) {
                message.error(error.message);
            } else {
                message.error(`Error: ${error.response?.status || 'Unknown'} - ${error.response?.statusText || 'An error occurred'}`);
            }
        } finally {
            setLoading(false);
        }
    };

  return (
        <div style={{ padding: '20px', maxWidth: '600px', margin: '0 auto' }}>
            <h2>Excel File Processor</h2>
            <ProductDataUpload  onUpload={handleFileUpload} />
            {loading && <LoadingIndicator />}
            {results && <ResultTable data={results} />}
        </div>
    );
}

export default App

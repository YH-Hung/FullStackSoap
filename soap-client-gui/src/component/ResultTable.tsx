import {ResultTableProps} from "../type/ComponentProps.ts";
import {Table} from "antd";

export function ResultTable({data} : ResultTableProps) {
    const columns = [
        {
            title: 'Success',
            dataIndex: 'isSuccess',
            key: 'isSuccess',
            render: (value: boolean) => (value ? 'Yes' : 'No'),
        },
        {
            title: 'Messages',
            dataIndex: 'message',
            key: 'message',
        },
    ];

    return <Table dataSource={data} columns={columns} rowKey={(_record, index) => index?.toString() ?? "0"} />;
}